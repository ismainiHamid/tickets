package ma.pub.ticketmanageservice.auditlog.mapper;

import ma.pub.ticketmanageservice.auditlog.AuditLogEntity;
import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuditLogMapperImpl implements AuditLogMapper {

    @Override
    public AuditLogDto toAuditLogDto(AuditLogEntity auditLogEntity) {
        return AuditLogDto.Builder.anAuditLogDto()
                .withId(auditLogEntity.getId())
                .withAction(auditLogEntity.getAction())
                .withUsername(auditLogEntity.getUser().getUsername())
                .withTicketId(auditLogEntity.getTicket().getTicketId())
                .build();
    }

    @Override
    public List<AuditLogDto> toAuditLogDtoList(List<AuditLogEntity> auditLogEntities) {
        return auditLogEntities.stream().map(this::toAuditLogDto).toList();
    }
}
