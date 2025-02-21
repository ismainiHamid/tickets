package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.auditLog.AuditLogEntity;
import ma.pub.ticketmanageservice.auditLog.AuditLogService;
import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketRequestDto;
import ma.pub.ticketmanageservice.ticket.dto.TicketResponseDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import ma.pub.ticketmanageservice.ticket.mapper.TicketMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public TicketResponseDto createTicket(TicketRequestDto ticketRequestDto) {
        TicketEntity ticketEntity = this.ticketJpaRepository.saveAndFlush(
                this.ticketMapper.toTicketEntity(ticketRequestDto)
        );

        this.auditLogService.createLogAction(AuditLogEntity.Builder.anAuditLogEntity()
                .withAction("Create a new ticket with title: " + ticketEntity.getTitle())
                .withTicket(ticketEntity)
                .build()
        );

        return this.ticketMapper.toTicketResponseDto(ticketEntity);
    }

    @Override
    @Transactional
    public TicketResponseDto updateTicket(UUID id, TicketRequestDto ticketRequestDto) throws NotFoundException {
        TicketEntity toTicketEntity = this.ticketMapper.toTicketEntity(ticketRequestDto);
        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ticket with id: " + id + " not found")
        );
        ticketEntity.setTitle(toTicketEntity.getTitle());
        ticketEntity.setDescription(toTicketEntity.getDescription());
        ticketEntity.setPriority(toTicketEntity.getPriority());
        ticketEntity.setCategory(toTicketEntity.getCategory());
        return this.ticketMapper.toTicketResponseDto(ticketEntity);
    }

    @Override
    public Page<TicketResponseDto> getAllTickets(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);
        return new PageImpl<>(this.ticketMapper.toTicketResponseDtoList(
                this.ticketJpaRepository.findAllBy(pageable)
        ), pageable, this.ticketJpaRepository.count());
    }

    @Override
    public List<TicketResponseDto> searchAndFilter(UUID id, Status status) {
        return this.ticketMapper.toTicketResponseDtoList(
                this.ticketJpaRepository.findByIdContainingIgnoreCaseOrStatusContainingIgnoreCase(id, status)
        );
    }

    @Override
    @Transactional
    public TicketResponseDto changeTicketStatus(UUID id, Status status) throws NotFoundException {
        TicketEntity ticketEntity = this.ticketJpaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ticket with id: " + id + " not found")
        );
        ticketEntity.setStatus(status);
        return this.ticketMapper.toTicketResponseDto(ticketEntity);
    }
}
