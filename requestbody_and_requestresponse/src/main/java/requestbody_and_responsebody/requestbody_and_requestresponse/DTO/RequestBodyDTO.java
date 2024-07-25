package requestbody_and_responsebody.requestbody_and_requestresponse.DTO;


import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import requestbody_and_responsebody.requestbody_and_requestresponse.Entity.User;


public class RequestBodyDTO {

  @NotEmpty
  @Size(
    min = 4,
    max = 10,
    message = "UserName Must be Minimum 4 Characters and Maximum 10 characters !!"
  )
  private String userName;

  @NotNull
  @Pattern(
    regexp = "^\\+91\\s[1-9][0-9]{9}$",
    message = "only 10-digit are allowed !!"
  )
  private String phoneNumber;

  @NotNull
  @Digits(
    integer = 2,
    fraction = 0,
    message = "Age must be a 2-digit Number !!"
  )
  @Min(value = 01, message = "Age Must be Greater than or Equal to  1 Year")
  @Max(value = 99, message = "Age Must be Less than or Equal to 99 Years")
  private Long age;

  public RequestBodyDTO() {}

  //this is used for sending data to the user
  //constructor
  public RequestBodyDTO(User user) {
    this.userName = user.getUserName();
    this.phoneNumber = user.getPhoneNumber();
    this.age = user.getAge();
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

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  //for sending data to the user entity
  public User toEntity(RequestBodyDTO requestBodyDTO) {
    User user = new User();
    System.out.println("DTO method is now calling " + requestBodyDTO.userName);
    user.setUserName(requestBodyDTO.userName);
    user.setPhoneNumber(requestBodyDTO.phoneNumber);
    user.setAge(requestBodyDTO.age);
    return user;
  }
}
