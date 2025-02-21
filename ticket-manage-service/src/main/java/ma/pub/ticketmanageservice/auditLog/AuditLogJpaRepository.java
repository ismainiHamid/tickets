package ma.pub.ticketmanageservice.auditLog;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuditLogJpaRepository extends JpaRepository<AuditLogEntity, UUID> {
    List<AuditLogEntity> findAllBy(Pageable pageable);

    List<AuditLogEntity> findAllByTicket_Id(UUID ticketId);
}
