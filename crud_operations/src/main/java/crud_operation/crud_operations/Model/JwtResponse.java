package crud_operation.crud_operations.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtResponse {

  private String jwtToken;
  private String userName;

  public String getJwtToken() {
    return jwtToken;
  }

  public JwtResponse(String jwtToken, String userName) {
    this.jwtToken = jwtToken;
    this.userName = userName;
  }

  @Override
  public String toString() {
    return "JwtResponse [jwtToken=" + jwtToken + ", userName=" + userName + "]";
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public JwtResponse() {}

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
