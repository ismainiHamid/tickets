package ma.pub.ticketmanageservice.auditLog;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditLogService {
    AuditLogEntity createLogAction(AuditLogEntity auditLogEntity);

    Page<AuditLogEntity> getAllLogs(int page, int size, String[] sort);

    List<AuditLogEntity> getAllLogsByTicketId(UUID ticketId);
}
