package ma.pub.ticketmanageservice.auditlog;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogService {
    void createLogAction(AuditLogEntity auditLogEntity);

    Page<AuditLogEntity> getAllLogs(int page, int size, String[] sort);

    List<AuditLogEntity> getAllLogsByTicketId(String ticketId);
}
