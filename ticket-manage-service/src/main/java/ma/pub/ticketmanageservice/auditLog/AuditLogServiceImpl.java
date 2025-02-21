package ma.pub.ticketmanageservice.auditLog;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogJpaRepository auditLogJpaRepository;

    public AuditLogServiceImpl(AuditLogJpaRepository auditLogJpaRepository) {
        this.auditLogJpaRepository = auditLogJpaRepository;
    }

    @Override
    public AuditLogEntity createLogAction(AuditLogEntity auditLogEntity) {
        return this.auditLogJpaRepository.saveAndFlush(auditLogEntity);
    }

    @Override
    public Page<AuditLogEntity> getAllLogs(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);
        return new PageImpl<>(this.auditLogJpaRepository.findAllBy(pageable),
                pageable, this.auditLogJpaRepository.count());
    }

    @Override
    public List<AuditLogEntity> getAllLogsByTicketId(UUID ticketId) {
        return this.auditLogJpaRepository.findAllByTicket_Id(ticketId);
    }
}
