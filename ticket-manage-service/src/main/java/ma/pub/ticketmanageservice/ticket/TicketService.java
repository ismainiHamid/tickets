package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketRequestDto;
import ma.pub.ticketmanageservice.ticket.dto.TicketResponseDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketService {
    TicketResponseDto createTicket(TicketRequestDto ticketRequestDto);

    TicketResponseDto updateTicket(UUID id, TicketRequestDto ticketRequestDto) throws NotFoundException;

    Page<TicketResponseDto> getAllTickets(int page, int size, String[] sort);

    List<TicketResponseDto> searchAndFilter(UUID id, Status status);

    TicketResponseDto changeTicketStatus(UUID id, Status status) throws NotFoundException;
}
