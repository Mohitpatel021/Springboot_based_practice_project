package crud_operation.crud_operations.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException {

  String msg;
  Throwable cause;
  HttpStatus status;

  public UserNotFoundException() {}

  public UserNotFoundException(String msg, Throwable cause, HttpStatus status) {
    this.msg = msg;
    this.cause = cause;
    this.status = status;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Throwable getCause() {
    return cause;
  }

  public void setCause(Throwable cause) {
    this.cause = cause;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}
