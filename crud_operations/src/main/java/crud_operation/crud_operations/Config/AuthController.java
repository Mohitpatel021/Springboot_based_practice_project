package crud_operation.crud_operations.Config;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud_operation.crud_operations.JWT.JwtHelper;
import crud_operation.crud_operations.Model.JwtRequest;
import crud_operation.crud_operations.Model.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(
  name = "Authentication",
  description = "This is for Handling Authentication Using JWT Token"
)
public class AuthController {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private JwtHelper helper;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Operation(
    description = "This End Point is for Login purpose",
    summary = "This EndPoint Helps to login Using a JWT Token"
  )
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody JwtRequest request)  {
    UserDetails userDetails = userDetailsService.loadUserByUsername(
      request.getEmail()
    );
      this.doAuthenticate(request.getEmail(), request.getPassword());
      String token = this.helper.generateToken(userDetails);
      JwtResponse response = JwtResponse
        .builder()
        .jwtToken(token)
        .userName(userDetails.getUsername())
        .build();
      return new ResponseEntity<>(response, HttpStatus.OK);
  }



  private void doAuthenticate(String userName, String password) {
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
      userName,
      password
    );
    try {
      manager.authenticate(authentication);
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  Boolean isPasswordValid(String oldPassword, String newPassword) {
    return passwordEncoder.matches(oldPassword, newPassword);
  }

  Boolean isUserNameValid(String oldUserName, String newUserName) {
    return Objects.equals(oldUserName, newUserName);
  }
}
