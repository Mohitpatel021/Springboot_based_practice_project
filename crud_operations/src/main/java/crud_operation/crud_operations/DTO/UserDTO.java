package crud_operation.crud_operations.DTO;

import crud_operation.crud_operations.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

  @NotBlank
  @Size(min = 4, max = 12)
  private String userName;

  @NotBlank
  //@Pattern(regexp = "^(+91[\\-\\s]?)?[789]\\d{9}$\r\n",message = "PhoneNumeber must follow the Pattern")
  @Size(max = 10, min = 10)
  private String phoneNumber;

  @Email
  //@Pattern(
    //regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\r\n",message = "Email Must follow the pattern")
  private String email;

  //Default constructor
  public UserDTO() {}

  //paramterised constructor
  public UserDTO(User user) {
    this.userName = user.getUserName();
    this.phoneNumber = user.getPhoneNumber();
    this.email = user.getEmail();
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  //Method for converting the UserDTO object to User Object
  public User toEntity(UserDTO userDTO) {
    User user = new User();
    user.setUserName(userDTO.userName);
    user.setPhoneNumber(userDTO.phoneNumber);
    user.setEmail(userDTO.email);
    return user;
  }
}
