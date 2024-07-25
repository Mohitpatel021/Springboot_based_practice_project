package role_based.role_based_jwt.Service;

import role_based.role_based_jwt.DTO.JwtAuthenticationResponse;
import role_based.role_based_jwt.DTO.SigninRequest;
import role_based.role_based_jwt.DTO.SignupRequest;
import role_based.role_based_jwt.Entity.User;

public interface AuthenticationService {
  User signup(SignupRequest signupRequest);
  JwtAuthenticationResponse signin(SigninRequest signinRequest);
}
