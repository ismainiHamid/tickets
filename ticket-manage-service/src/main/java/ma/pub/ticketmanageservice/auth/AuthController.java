package ma.pub.ticketmanageservice.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import ma.pub.ticketmanageservice.auth.dto.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
@Tag(name = "Auth", description = "Manage the auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        return this.authService.login(loginRequestDto, request);
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return this.authService.logout(request);
    }

    @GetMapping(path = "/me")
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        return this.authService.getCurrentUser(request);
    }
}
