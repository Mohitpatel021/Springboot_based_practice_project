package role_based.role_based_jwt.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import role_based.role_based_jwt.DTO.SigninRequest;
import role_based.role_based_jwt.DTO.SignupRequest;
import role_based.role_based_jwt.Entity.User;

import role_based.role_based_jwt.Repository.UserRepository;
import role_based.role_based_jwt.Service.AuthenticationService;

@RestController
@RequestMapping("/auth")
@Tag(
  name = "Authentication",
  description = "This is the controller for Authentication purpose"
)
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(
    @Valid @RequestBody SignupRequest signupRequest
  ) {
    return ResponseEntity.ok(authenticationService.signup(signupRequest));
  }

  @PostMapping("/signin")
  public ResponseEntity<?> signin(
    @Valid @RequestBody SigninRequest signinRequest
  ) {
    Optional<User> user = userRepository
      .findByEmail(signinRequest.getEmail());
      //.orElseThrow(() -> new UserNotFoundExceptions("User Not Found  Email !!Please Register First"));
      System.out.println(user);
    return ResponseEntity.ok(authenticationService.signin(signinRequest));
  }
}
