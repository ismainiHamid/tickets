package ma.pub.ticketmanageservice.ticket;

import ma.pub.ticketmanageservice.ticket.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketJpaRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findAllBy(Pageable pageable);

    List<TicketEntity> findByStatus(Status status);

    List<TicketEntity> findByIdContainingIgnoreCaseOrStatusContainingIgnoreCase(UUID id, Status status);
}
