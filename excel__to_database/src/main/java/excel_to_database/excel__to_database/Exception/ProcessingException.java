package excel_to_database.excel__to_database.Exception;

public class ProcessingException extends RuntimeException {

  public ProcessingException(String message) {
    super(message);
  }

  public ProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
