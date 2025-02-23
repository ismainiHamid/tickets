package ma.pub.ticketmanageservice.auth;

import jakarta.servlet.http.HttpServletRequest;
import ma.pub.ticketmanageservice.auth.dto.LoginRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()
                    )
            );

            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            request.getSession().setAttribute("USER", authentication.getPrincipal());

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        if (Objects.isNull(securityContext) || Objects.isNull(securityContext.getAuthentication()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


        Object principal = securityContext.getAuthentication().getPrincipal();
        if (Objects.nonNull(principal)) return ResponseEntity.ok(principal);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
