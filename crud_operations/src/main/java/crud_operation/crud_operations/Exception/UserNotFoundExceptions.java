package crud_operation.crud_operations.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundExceptions extends RuntimeException {

  public UserNotFoundExceptions(String messgae) {
    super(messgae);
  }

  public UserNotFoundExceptions(String messgae, Throwable cause,HttpStatus status) {
    super(messgae, cause);
  }
}
