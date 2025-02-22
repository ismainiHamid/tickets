package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.exceptions.AlreadyExistsException;
import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto) throws AlreadyExistsException;

    TicketDto updateTicket(UUID id, TicketDto ticketDto) throws AlreadyExistsException, NotFoundException;

    Page<TicketDto> getAllTickets(int page, int size, String[] sort);

    List<TicketDto> searchAndFilter(String id, Status status);

    TicketDto changeTicketStatus(UUID id, Status status) throws NotFoundException;
}
