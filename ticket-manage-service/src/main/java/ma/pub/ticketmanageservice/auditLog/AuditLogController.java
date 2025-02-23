package ma.pub.ticketmanageservice.auditlog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "/v1/auditLogs")
@Tag(name = "Audit log", description = "Manage audit logs")
public class AuditLogController {
    private final AuditLogService auditLogService;

    public AuditLogController(final AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @Operation(summary = "Get all audit logs", description = "Fetch all audit logs")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<Page<AuditLogDto>> getAllAuditLogs(int page, int size, String[] sort) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.auditLogService.getAllLogs(page, size, sort));
    }
}
