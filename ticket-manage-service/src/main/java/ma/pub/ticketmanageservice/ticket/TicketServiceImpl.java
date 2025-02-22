package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.auditlog.AuditLogEntity;
import ma.pub.ticketmanageservice.auditlog.AuditLogService;
import ma.pub.ticketmanageservice.exceptions.AlreadyExistsException;
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
    private final AuditLogService auditLogService;

    public TicketServiceImpl(TicketJpaRepository ticketJpaRepository, AuditLogService auditLogService, TicketMapper ticketMapper) {
        this.ticketJpaRepository = ticketJpaRepository;
        this.auditLogService = auditLogService;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) throws AlreadyExistsException {
        TicketEntity toTicketEntity = this.ticketMapper.toTicketEntity(ticketDto);

        if (this.ticketJpaRepository.existsByIdIgnoreCase(toTicketEntity.getId()))
            throw new AlreadyExistsException("The tickets with id: " + toTicketEntity.getId() + ", already exists.");

        TicketEntity ticketEntity = this.ticketJpaRepository.saveAndFlush(toTicketEntity);

        this.auditLogService.createLogAction(AuditLogEntity.Builder.anAuditLogEntity()
                .withAction("Create a new ticket with title: " + ticketEntity.getTitle())
                .withTicket(ticketEntity)
                .build()
        );

        return this.ticketMapper.toTicketDto(ticketEntity);
    }

    @Override
    @Transactional
    public TicketDto updateTicket(UUID id, TicketDto ticketDto) throws AlreadyExistsException, NotFoundException {
        TicketEntity toTicketEntity = this.ticketMapper.toTicketEntity(ticketDto);

        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Can't update the ticket with id: " + id + ", dose not exists.")
        );

        if (!ticketEntity.getTitle().equals(toTicketEntity.getTitle()))
            if (this.ticketJpaRepository.existsByIdIgnoreCase(toTicketEntity.getId()))
                throw new AlreadyExistsException("The tickets with id: " + toTicketEntity.getId() + ", already exists.");

        ticketEntity.setTitle(toTicketEntity.getTitle());
        ticketEntity.setDescription(toTicketEntity.getDescription());
        ticketEntity.setPriority(toTicketEntity.getPriority());
        ticketEntity.setCategory(toTicketEntity.getCategory());

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
    public List<TicketDto> searchAndFilter(String id, Status status) {
        return this.ticketMapper.toTicketDtoList(
                this.ticketJpaRepository.findByIdContainingIgnoreCaseOrStatus(id, status)
        );
    }

    @Override
    @Transactional
    public TicketDto changeTicketStatus(UUID id, Status status) throws NotFoundException {
        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ticket with id: " + id + " not found")
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
