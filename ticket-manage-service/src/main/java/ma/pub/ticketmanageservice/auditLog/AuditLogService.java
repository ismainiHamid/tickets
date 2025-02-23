package ma.pub.ticketmanageservice.auditlog;

import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogService {
    Page<AuditLogDto> getAllLogs(int page, int size, String[] sort);
}
