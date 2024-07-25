package role_based.role_based_jwt.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import role_based.role_based_jwt.Entity.User;

import role_based.role_based_jwt.Exception.UserNotFoundExceptions;
import role_based.role_based_jwt.Repository.UserRepository;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Admin", description = "Admin Controller For Admin Operation")

@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

  @Autowired
  private final UserRepository userRepository;

  //Fetch All the user
  @Operation(
    description = "This is the Fetch All End point for Fetching all the User",
    summary = "With the help of this endpoint we can fetch all the user present in the Database"
  )
  @GetMapping("/fetch-All")
  public ResponseEntity<List<User>> getAllUser() {
    List<User> users = userRepository.findAll();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Operation(
    description = "This is the end point for fetch a user By UserId",
    summary = "With the help of this endpoint we Can fetch a user By UserID"
  )
  //search user by UserId
  @GetMapping("/profile/{Id}")
  public ResponseEntity<User> getUserById(@PathVariable Long Id) {
    User user = userRepository
      .findById(Id)
      .orElseThrow(() ->
        new UsernameNotFoundException("User with this UserId is Not found")
      );
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @Operation(
    description = "This is the end point for fetch a user By UserName",
    summary = "With the help of this endpoint we Can fetch a user By UserName"
  )
  //search user By userName
  @GetMapping("/fetch-user")
  public ResponseEntity<User> getUserByUserName(
    @RequestParam String firstName
  ) {
    User user = userRepository.findByFirstName(firstName);
    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("delete/{Id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long Id) {
    User user = userRepository
      .findById(Id)
      .orElseThrow(() ->
        new UserNotFoundExceptions(
          "User Not Found With this UserId",
          HttpStatus.NOT_FOUND
        )
      );
    userRepository.deleteById(user.getId());
    return new ResponseEntity<>("User Deleted Successsfully !!", HttpStatus.OK);
  }
}
