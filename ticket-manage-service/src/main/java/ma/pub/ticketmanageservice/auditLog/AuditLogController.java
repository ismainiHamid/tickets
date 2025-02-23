package ma.pub.ticketmanageservice.auditlog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import ma.pub.ticketmanageservice.exceptions.ExceptionResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "/v1/auditLogs")
@Tag(name = "Audit log", description = "Manage audit logs")
public class AuditLogController {
    private final AuditLogService auditLogService;

    public AuditLogController(final AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Operation(
            summary = "Get all audit logs",
            description = "Fetch all audit logs",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Audi logs successfully fetched",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<Page<AuditLogDto>> getAllAuditLogs(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "action,DESC") String[] sort) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.auditLogService.getAllLogs(page, size, sort));
    }
}
