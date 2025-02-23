package ma.pub.ticketmanageservice.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.user.dto.RegisterRequestDto;
import ma.pub.ticketmanageservice.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/users")
@Tag(name = "User", description = "Manage users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create new user", description = "Register new user")
    @PostMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.registerUser(registerRequestDto));
    }

    @Operation(summary = "Get all users", description = "Fetch all users")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "username,DESC") String[] sort) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.getAllUsers(page, size, sort));
    }
}
