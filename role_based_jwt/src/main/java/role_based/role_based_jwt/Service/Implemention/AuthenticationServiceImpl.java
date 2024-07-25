package role_based.role_based_jwt.Service.Implemention;

//import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import role_based.role_based_jwt.DTO.JwtAuthenticationResponse;
import role_based.role_based_jwt.DTO.SigninRequest;
import role_based.role_based_jwt.DTO.SignupRequest;
import role_based.role_based_jwt.Entity.User;
import role_based.role_based_jwt.Exception.UserNotFoundExceptions;
import role_based.role_based_jwt.Repository.UserRepository;
import role_based.role_based_jwt.Service.AuthenticationService;
import role_based.role_based_jwt.Service.JWTService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private  UserRepository userRepository;

  @Autowired
  private  AuthenticationManager authenticationManager;

  @Autowired
  private  PasswordEncoder passwordEncoder;

  @Autowired
  private  JWTService jwtService;

  //for saving a user

  public User signup(SignupRequest signupRequest) {
    User user = new User();
    user.setFirstName(signupRequest.getFirstName());
    user.setSecondName(signupRequest.getLastName());
    user.setEmail(signupRequest.getEmail());
    user.setRole(signupRequest.getRole());
    user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    return userRepository.save(user);
  }

  public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        signinRequest.getEmail(),
        signinRequest.getPassword()
      )
    );

    var user = userRepository
      .findByEmail(signinRequest.getEmail())
      .orElseThrow(() ->
        new UserNotFoundExceptions("User Not Found With this UserName!!")
      );

    var jwt = jwtService.generateToken(user);
    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setToken(jwt);
    jwtAuthenticationResponse.setEmail(signinRequest.getEmail());
    // jwtAuthenticationResponse.setRefreshToken(refreshToken);
    return jwtAuthenticationResponse;
  }
}
