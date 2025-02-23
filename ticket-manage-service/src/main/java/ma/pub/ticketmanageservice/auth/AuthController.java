package ma.pub.ticketmanageservice.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import ma.pub.ticketmanageservice.auth.dto.LoginRequestDto;
import ma.pub.ticketmanageservice.exceptions.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth")
@Tag(name = "Auth", description = "Manage the auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Login",
            description = "User login by username and password",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successfully"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        return this.authService.login(loginRequestDto, request);
    }

    @Operation(
            summary = "Logout",
            description = "User logout",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logout successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        return this.authService.logout(request);
    }

    @Operation(
            summary = "Get user authenticated info",
            description = "Get user authenticated info like: username, role",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get user successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
    @GetMapping(path = "/me")
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT') or hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        return this.authService.getCurrentUser(request);
    }
}
