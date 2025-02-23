package ma.pub.ticketmanageservice.auth;

import jakarta.servlet.http.HttpServletRequest;
import ma.pub.ticketmanageservice.auth.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthService {
    ResponseEntity<?> login(LoginRequestDto loginRequestDto, HttpServletRequest request);

    ResponseEntity<String> logout(HttpServletRequest request);

    ResponseEntity<?> getCurrentUser(HttpServletRequest request);
}
