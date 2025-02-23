package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.auditlog.AuditLogEntity;
import ma.pub.ticketmanageservice.auditlog.AuditLogJpaRepository;
import ma.pub.ticketmanageservice.exceptions.AlreadyExistsException;
import ma.pub.ticketmanageservice.exceptions.BadRequestException;
import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import ma.pub.ticketmanageservice.ticket.mapper.TicketMapper;
import ma.pub.ticketmanageservice.user.UserEntity;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketJpaRepository ticketJpaRepository;
    private final TicketMapper ticketMapper;
    private final AuditLogJpaRepository auditLogJpaRepository;

    public TicketServiceImpl(TicketJpaRepository ticketJpaRepository, AuditLogJpaRepository auditLogJpaRepository, TicketMapper ticketMapper) {
        this.ticketJpaRepository = ticketJpaRepository;
        this.auditLogJpaRepository = auditLogJpaRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    @Transactional
    public TicketDto createTicket(TicketDto ticketDto) throws AlreadyExistsException, BadRequestException {
        TicketEntity toTicketEntity = this.ticketMapper.toTicketEntity(ticketDto);

        if (this.ticketJpaRepository.existsByTicketIdIgnoreCase(toTicketEntity.getTicketId()))
            throw new AlreadyExistsException("The tickets with id: " + toTicketEntity.getTicketId() + ", already exists.");

        UserEntity connectedUser = this.getConnectedUser().orElseThrow(() ->
                new BadRequestException("Error the user connection.")
        );

        toTicketEntity.setUser(connectedUser);
        TicketEntity ticketEntity = this.ticketJpaRepository.saveAndFlush(toTicketEntity);

        this.auditLogJpaRepository.saveAndFlush(AuditLogEntity.Builder.anAuditLogEntity()
                .withAction("Create a new ticket with title: " + ticketEntity.getTitle())
                .withTicket(ticketEntity)
                .withUser(connectedUser)
                .build()
        );

        return this.ticketMapper.toTicketDto(ticketEntity);
    }

    @Override
    @Transactional
    public TicketDto updateTicket(UUID id, TicketDto ticketDto) throws AlreadyExistsException, BadRequestException, NotFoundException {
        TicketEntity toTicketEntity = this.ticketMapper.toTicketEntity(ticketDto);

        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Can't update the ticket with id: " + id + ", dose not exists.")
        );

        if (!ticketEntity.getTicketId().equals(toTicketEntity.getTicketId()))
            if (this.ticketJpaRepository.existsByTicketIdIgnoreCase(toTicketEntity.getTicketId()))
                throw new AlreadyExistsException("The tickets with id: " + toTicketEntity.getTicketId() + ", already exists.");

        UserEntity connectedUser = this.getConnectedUser().orElseThrow(() ->
                new BadRequestException("Error the user connection.")
        );

        ticketEntity.setTitle(toTicketEntity.getTitle());
        ticketEntity.setDescription(toTicketEntity.getDescription());
        ticketEntity.setPriority(toTicketEntity.getPriority());
        ticketEntity.setCategory(toTicketEntity.getCategory());
        ticketEntity.setUser(connectedUser);

        this.auditLogJpaRepository.saveAndFlush(AuditLogEntity.Builder.anAuditLogEntity()
                .withAction("Updated ticket with title: " + ticketEntity.getTitle())
                .withTicket(ticketEntity)
                .withUser(connectedUser)
                .build()
        );

        return this.ticketMapper.toTicketDto(ticketEntity);
    }

    @Override
    public Page<TicketDto> getAllTickets(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);

        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);

        return new PageImpl<>(this.ticketMapper.toTicketDtoList(
                this.ticketJpaRepository.findAllBy(pageable)
        ), pageable, this.ticketJpaRepository.count());
    }

    @Override
    public Page<TicketDto> getAllTicketsByUser_username(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);

        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);

        UserEntity connectedUser = this.getConnectedUser().orElseThrow(() ->
                new BadRequestException("Error the user auth.")
        );

        return new PageImpl<>(this.ticketMapper.toTicketDtoList(
                this.ticketJpaRepository.findAllByUser_Username(connectedUser.getUsername(), pageable)
        ), pageable, this.ticketJpaRepository.countAllByUser_Username(connectedUser.getUsername()));
    }

    @Override
    public List<TicketDto> searchAndFilter(String ticketId, Status status) {
        return this.ticketMapper.toTicketDtoList(
                this.ticketJpaRepository.findByTicketIdContainingIgnoreCaseOrStatus(ticketId, status)
        );
    }

    @Override
    @Transactional
    public TicketDto changeTicketStatus(UUID id, Status status) throws NotFoundException {
        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ticket with id: " + id + " not found")
        );

        this.auditLogJpaRepository.saveAndFlush(AuditLogEntity.Builder.anAuditLogEntity()
                .withAction("Change ticket with status to: " + status)
                .withTicket(ticketEntity)
                .withUser(this.getConnectedUser().orElseThrow(() ->
                        new BadRequestException("Error the user connection.")
                ))
                .build()
        );

        ticketEntity.setStatus(status);
        return this.ticketMapper.toTicketDto(ticketEntity);
    }

    private Optional<UserEntity> getConnectedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserEntity)
                return Optional.of((UserEntity) principal);
        }
        return Optional.empty();
    }
}
