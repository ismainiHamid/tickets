package ma.pub.ticketmanageservice.auditlog;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogJpaRepository auditLogJpaRepository;

    public AuditLogServiceImpl(AuditLogJpaRepository auditLogJpaRepository) {
        this.auditLogJpaRepository = auditLogJpaRepository;
    }

    @Override
    public void createLogAction(AuditLogEntity auditLogEntity) {
        this.auditLogJpaRepository.saveAndFlush(auditLogEntity);
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
    public List<AuditLogEntity> getAllLogsByTicketId(String ticketId) {
        return this.auditLogJpaRepository.findAllByTicket_Id(ticketId);
    }
}
