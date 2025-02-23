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

    List<TicketEntity> findAllByUser_Username(String username, Pageable pageable);

    List<TicketEntity> findByTicketIdContainingIgnoreCaseOrStatus(String id, Status status);

    long countAllByUser_Username(String username);

    boolean existsByTicketIdIgnoreCase(String ticketId);
}
