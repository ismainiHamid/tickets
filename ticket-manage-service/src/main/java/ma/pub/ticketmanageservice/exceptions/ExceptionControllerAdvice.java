package ma.pub.ticketmanageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.NOT_FOUND.value())
                        .withError("NOT_FOUND")
                        .withErrorMessage(e.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.Builder.anExceptionResponse()
                        .withCode((short) HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .withError("Internal Server Error")
                        .withErrorMessage(ex.getMessage())
                        .withTimestamp(LocalDateTime.now())
                        .build()
                );
    }
}
