package role_based.role_based_jwt.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import role_based.role_based_jwt.Entity.Role;

public class SignupRequest {

  @NotEmpty(message = "Please Enter Your FirstName!!")
  private String firstName;

  @NotEmpty(message = "please Enter Your LastName")
  private String lastName;

  @NotEmpty(message = "Please Enter Your Email ")
  @Email(message = "Please check your Email!!")
  private String email;

  @NotEmpty(message = "Please Enter Your Password")
  @Size(min = 4, max = 6, message = "Password should be 4 to 6 char long")
  private String password;

  private Role role;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

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

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
