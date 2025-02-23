package ma.pub.ticketmanageservice.auditlog.mapper;

import ma.pub.ticketmanageservice.auditlog.AuditLogEntity;
import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogMapper {
    AuditLogDto toAuditLogDto(AuditLogEntity auditLogEntity);

    List<AuditLogDto> toAuditLogDtoList(List<AuditLogEntity> auditLogEntities);
}
