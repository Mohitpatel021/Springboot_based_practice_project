package excel_to_database.excel__to_database.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProcessingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleException(
    ProcessingException excelProcessingException
  ) {
    String errorMessage = excelProcessingException.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
}
