package role_based.role_based_jwt.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import role_based.role_based_jwt.Entity.User;
import role_based.role_based_jwt.Repository.UserRepository;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasRole('USER')")
@Tag(
  name = "User",
  description = "This is the User Controller"
)
public class UserController {
@Autowired
  private final UserRepository userRepository;

  //Get profile of a particular user Username
  @Operation(
    description = "This is the end point for fetch a user By User's Firstname",
    summary = "With the help of this endpoint we Can fetch a user By User's FirstName"
  )
  @GetMapping("/{firstName}")
  public ResponseEntity<User> getProfile(@PathVariable String firstName) {
    User user = userRepository.findByFirstName(firstName);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  //delete a user by UserId

  @Operation(
    description = "This is the end point for Delete a user By UserId",
    summary = "With the help of this endpoint we Can delete a user By UserID"
  )
  @DeleteMapping("/delete/{Id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long Id) {
    //User user = userRepository.findById(Id).get();
    userRepository.deleteById(Id);
    return new ResponseEntity<>("User Deleted Successfully!!!", HttpStatus.OK);
  }

  @Operation(
    description = "This is the end point for update a user By User's Firstname",
    summary = "With the help of this endpoint we Can update a user By User's FirstName"
  )
  //update a User  using firstname
  @PutMapping("/update/{firstName}")
  public ResponseEntity<String> updateUser(
    @Valid @RequestBody User updatedUser,
    @PathVariable String firstName
  ) {
    User existingUser = userRepository.findByFirstName(firstName);
    if (existingUser != null) {
      existingUser.setEmail(updatedUser.getEmail());
      existingUser.setFirstName(updatedUser.getFirstName());
      existingUser.setSecondName(updatedUser.getSecondName());
      existingUser.setPassword(updatedUser.getPassword());
    }
    return new ResponseEntity<>(
      "User Updation is Successfully Done!!",
      HttpStatus.OK
    );
  }
}
