package ma.pub.ticketmanageservice.user.config;

import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserDetailsService {
    UserDetails loadUserByUsername(String username) throws NotFoundException;
}
