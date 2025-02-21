package ma.pub.ticketmanageservice.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.ticket.dto.TicketRequestDto;
import ma.pub.ticketmanageservice.ticket.dto.TicketResponseDto;
import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tickets")
@Tag(name = "Ticket", description = "Manage Tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Save or update tickets", description = "Save or update tickets")
    @PutMapping
    public ResponseEntity<TicketResponseDto> createOrUpdateTicket(@RequestParam(required = false) UUID id,
                                                                  @RequestBody TicketRequestDto ticketRequestDto) throws NotFoundException {
        return (Objects.isNull(id)) ?
                ResponseEntity.status(HttpStatus.CREATED)
                        .body(this.ticketService.createTicket(ticketRequestDto)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(this.ticketService.updateTicket(id, ticketRequestDto));
    }

    @Operation(summary = "Get all tickets", description = "Fetch all tickets")
    @GetMapping
    public ResponseEntity<Page<TicketResponseDto>> getAllTickets(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size,
                                                                 @RequestParam(defaultValue = "title,DESC") String[] sort) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.ticketService.getAllTickets(page, size, sort)
        );
    }

    @Operation(summary = "Search & filter", description = "Search & filter tickets")
    @GetMapping(path = "/search")
    public ResponseEntity<List<TicketResponseDto>> searchAndFilter(@RequestParam UUID id,
                                                                   @RequestParam Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.ticketService.searchAndFilter(id, status)
        );
    }

    @Operation(summary = "Change ticket status", description = "Change ticket status By ID")
    @PatchMapping(path = "/{id}")
    public ResponseEntity<TicketResponseDto> updateTicketStatus(@PathVariable(value = "id") UUID id,
                                                                @RequestParam Status status) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.ticketService.changeTicketStatus(id, status)
        );
    }
}
