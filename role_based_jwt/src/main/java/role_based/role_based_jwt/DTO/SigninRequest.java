package role_based.role_based_jwt.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class SigninRequest {

  @NotBlank(message = "Email Should not be Empty !!")
  @Email(message = "Please Check Your Email!!")
  private String email;

  @NotBlank(message = "Password Should not be Empty !!")
  private String password;

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
}
