package ma.pub.ticketmanageservice.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findAllBy(Pageable pageable);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
