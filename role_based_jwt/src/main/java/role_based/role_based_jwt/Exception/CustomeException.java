package role_based.role_based_jwt.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomeException {

    @ExceptionHandler(UserNotFoundExceptions.class)
  public ResponseEntity<Object> UserNotFoundException(
    UserNotFoundExceptions userNotFoundExceptions
  ) {
    UserNotFoundException userNotFoundException = new UserNotFoundException(
      userNotFoundExceptions.getMessage(),
      userNotFoundExceptions.getCause(),
      HttpStatus.NOT_FOUND
    );
    return new ResponseEntity<>(userNotFoundException, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        });
        return ResponseEntity.badRequest().body(errorMessage.toString().trim());
    }
}
