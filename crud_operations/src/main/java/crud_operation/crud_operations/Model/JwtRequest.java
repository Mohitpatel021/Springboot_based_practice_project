package crud_operation.crud_operations.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
public class JwtRequest {

  @NotEmpty(message = "Please enter Username")
  @Size(min = 5, max = 5, message = "Check your Username")
  private String email;

  @NotEmpty(message = "Please enter Password")
  @Size(min = 5, max = 5, message = "Check your password")
  private String password;

  public JwtRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public JwtRequest() {}

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "JwtRequest [email=" + email + ", password=" + password + "]";
  }
}
