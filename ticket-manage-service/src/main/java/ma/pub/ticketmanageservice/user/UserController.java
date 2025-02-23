package ma.pub.ticketmanageservice.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.pub.ticketmanageservice.exceptions.DuplicatedPasswordException;
import ma.pub.ticketmanageservice.exceptions.ExceptionResponse;
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

    @Operation(
            summary = "Create new user",
            description = "Register new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "409", description = "The password don't matcher",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ROLE_IT_SUPPORT')")
    public ResponseEntity<UserDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto) throws DuplicatedPasswordException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.registerUser(registerRequestDto));
    }

    @Operation(
            summary = "Get All user",
            description = "Fetch all user with page, size and sorted",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully users fetched",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            }
    )
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
