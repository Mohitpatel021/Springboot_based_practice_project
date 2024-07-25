package crud_operation.crud_operations.Exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

  //in this annotation we use that class which will extends using Runtime Exception
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
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleArgsException(
    MethodArgumentNotValidException ex
  ){
    Map<String, String> response = new HashMap<>();
    ex
      .getBindingResult()
      .getFieldErrors()
      .forEach(error -> {
        response.put(error.getField(), error.getDefaultMessage());
      });
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
