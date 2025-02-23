package ma.pub.ticketmanageservice.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.exceptions.AlreadyExistsException;
import ma.pub.ticketmanageservice.exceptions.BadRequestException;
import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "/v1/tickets")
@Tag(name = "Ticket", description = "Manage Tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Save or update tickets", description = "Save or update tickets")
    @PutMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<TicketDto> createOrUpdateTicket(@RequestParam(required = false) UUID id,
                                                          @RequestBody TicketDto ticketDto) throws AlreadyExistsException, BadRequestException, NotFoundException {
        return (Objects.isNull(id)) ?
                ResponseEntity.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.ticketService.createTicket(ticketDto)) :
                ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(this.ticketService.updateTicket(id, ticketDto));
    }

    @Operation(summary = "Get all tickets", description = "Fetch all tickets")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<Page<TicketDto>> getAllTickets(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(defaultValue = "title,DESC") String[] sort) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.ticketService.getAllTickets(page, size, sort));
    }

    @Operation(summary = "Get all tickets by user", description = "Fetch all tickets by user")
    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<Page<TicketDto>> getAllTicketsByUser(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "10") int size,
                                                               @RequestParam(defaultValue = "title,DESC") String[] sort) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.ticketService.getAllTicketsByUser_username(page, size, sort));
    }


    @Operation(summary = "Search & filter", description = "Search & filter tickets")
    @GetMapping(path = "/search")
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<List<TicketDto>> searchAndFilter(@RequestParam String ticketId,
                                                           @RequestParam Status status) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.ticketService.searchAndFilter(ticketId, status));
    }

    @Operation(summary = "Change ticket status", description = "Change ticket status By ID")
    @PatchMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<TicketDto> updateTicketStatus(@PathVariable(value = "id") UUID id,
                                                        @RequestParam Status status) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.ticketService.changeTicketStatus(id, status));
    }
}
