package ma.pub.ticketmanageservice.auditlog;

import ma.pub.ticketmanageservice.auditlog.dto.AuditLogDto;
import ma.pub.ticketmanageservice.auditlog.mapper.AuditLogMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogJpaRepository auditLogJpaRepository;
    private final AuditLogMapper auditLogMapper;

    public AuditLogServiceImpl(AuditLogJpaRepository auditLogJpaRepository, AuditLogMapper auditLogMapper) {
        this.auditLogJpaRepository = auditLogJpaRepository;
        this.auditLogMapper = auditLogMapper;
    }

    @Override
    public Page<AuditLogDto> getAllLogs(int page, int size, String[] sort) {
        Sort sortPage = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);

        Pageable pageable = (size == -1) ? Pageable.unpaged(sortPage) :
                PageRequest.of(page, size, sortPage);

        return new PageImpl<>(this.auditLogMapper.toAuditLogDtoList(
                this.auditLogJpaRepository.findAllBy(pageable)
                ),pageable, this.auditLogJpaRepository.count());
    }
}
