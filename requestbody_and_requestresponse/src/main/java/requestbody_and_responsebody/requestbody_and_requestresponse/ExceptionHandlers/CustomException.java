package requestbody_and_responsebody.requestbody_and_requestresponse.ExceptionHandlers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomException {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleArgsException(
    MethodArgumentNotValidException ex
  ) {
    Map<String, String> response = new HashMap<>();
    ex
      .getBindingResult()
      .getFieldErrors()
      .forEach(error -> {
        response.put(error.getField(), error.getDefaultMessage());
      });
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public Map<String, String> handleTypeArgsException(
    UnexpectedTypeException er
  ) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("message", er.getMessage());
    return errorMap;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> userNotFoundException(
    EntityNotFoundException ene
  ) {
    String errorMSG = ene.getLocalizedMessage();
    return new ResponseEntity<>(errorMSG, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<String> nullPointerExceptionHandler(
    NullPointerException nullPointerException
  ) {
    return new ResponseEntity<>("UserName not found.", HttpStatus.NOT_FOUND);
  }
}
