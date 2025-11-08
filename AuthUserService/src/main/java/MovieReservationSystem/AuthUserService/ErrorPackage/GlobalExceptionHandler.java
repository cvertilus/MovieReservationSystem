package MovieReservationSystem.AuthUserService.ErrorPackage;


import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
     ErrorResponse errorResponse =  ErrorResponse.builder()
                .message(ex.getMessage())
                .status(400)
                .build();
     return ResponseEntity.badRequest().body(errorResponse);
    }

    //400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse =  ErrorResponse.builder()
                .message(ex.getMessage())
                .status(400)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse errorResponse =  ErrorResponse.builder()
                .message(ex.getMessage())
                .status(404)
                .build();
        return ResponseEntity.status(404).body(errorResponse);

    }

    //400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse =  ErrorResponse.builder()
                .message(errorMessage)
                .status(400)
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }

    //500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Internal Server Error: " + ex.getMessage())
                .status(500)
                .build();
        return ResponseEntity.status(500).body(errorResponse);
    }


}
