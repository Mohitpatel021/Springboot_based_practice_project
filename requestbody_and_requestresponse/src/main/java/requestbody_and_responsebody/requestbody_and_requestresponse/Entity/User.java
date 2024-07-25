package requestbody_and_responsebody.requestbody_and_requestresponse.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userID;

  
  private String userName;
  private Long age;
  private String phoneNumber;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getAge() {
    return age;
  }

  @Override
  public String toString() {
    return (
      "User [userName=" +
      userName +
      ", age=" +
      age +
      ", phoneNumber=" +
      phoneNumber +
      "]"
    );
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
