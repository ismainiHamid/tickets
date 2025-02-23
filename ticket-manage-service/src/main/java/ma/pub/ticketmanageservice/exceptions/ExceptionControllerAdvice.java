package ma.pub.ticketmanageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.NOT_FOUND.value())
                        .withError("NOT_FOUND")
                        .withErrorMessage(e.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleAlreadyExistsException(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.CONFLICT.value())
                        .withError("ALREADY_EXISTS")
                        .withErrorMessage(e.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleAccessDeniedException(AuthorizationDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.UNAUTHORIZED.value())
                        .withError("UNAUTHORIZED")
                        .withErrorMessage(ex.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(DuplicatedPasswordException.class)
    public ResponseEntity<ExceptionResponse> handleNotDuplicatedPasswordException(DuplicatedPasswordException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.CONFLICT.value())
                        .withError("DUPLICATED_PASSWORD")
                        .withErrorMessage(ex.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.BAD_REQUEST.value())
                        .withError("BAD_REQUEST")
                        .withErrorMessage(ex.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withError("INTERNAL_SERVER_ERROR")
                        .withErrorMessage(ex.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }
}
