package ma.pub.ticketmanageservice.security;

import ma.pub.ticketmanageservice.exceptions.NotFoundException;
import ma.pub.ticketmanageservice.user.UserEntity;
import ma.pub.ticketmanageservice.user.UserJpaRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    public CustomUserDetailsServiceImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        UserEntity user = this.userJpaRepository.findByUsername(username).orElseThrow(() ->
                new NotFoundException("The username or password is incorrect.")
        );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
