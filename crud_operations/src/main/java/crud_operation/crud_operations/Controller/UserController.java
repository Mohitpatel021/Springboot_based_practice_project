package crud_operation.crud_operations.Controller;

import crud_operation.crud_operations.DTO.UserDTO;
import crud_operation.crud_operations.Service.UserServiceImple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(
  name="Users",
  description = "This is the User Controller for Handling a User related operation"
)
public class UserController {

  @Autowired
  private UserServiceImple userService;

  //THis is for saving a new User
  @Operation(
    description="This is the User Post EndPoint For saving a User",
    summary="This is the User saving EndPoint"
  )
  @PostMapping("/save")
  public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
    System.out.println(
      "controller method call for username " + userDTO.getUserName()
    );
    UserDTO newUserDTO = userService.saveUser(userDTO);
    if (newUserDTO != null) {
      return new ResponseEntity<>(newUserDTO, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
    }
  }

  @Operation(
    description="This is the Get Endpoint for Fetching a User From  the Database",
    summary = "This End point will return the User Profile "
  )
  //Htis is for fetching a user by userName
  @GetMapping("/profile")
  public ResponseEntity<UserDTO> getUser(@RequestParam String  userName) {
    //System.out.println("Controller method called" + userDTO.getUserName());
    //String userName=userDTO.getUserName();
    UserDTO userDTOs = userService.getUserByUserName(userName);
    return new ResponseEntity<>(userDTOs, HttpStatus.OK);
  }

  @Operation(
    description = "This endPoint will return the User profile By using UserID",
    summary = "This will fetch the user by Using UserID"
  )
  //Fetch User By User Id
  @GetMapping("/profile/{userId}")
  public ResponseEntity<UserDTO> fetchUserByID(@PathVariable Long userId) {
    UserDTO userDTO = userService.getUserById(userId);
    if (userDTO != null) {
      return new ResponseEntity<>(userDTO, HttpStatus.FOUND);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Operation(
    description = "This is the GetEndPoint for fetching All the User",
    summary = "This endpoint will return all the users in the database"
  )
  //Fetching all the users
  @GetMapping("/fetch-All")
  public ResponseEntity<List<UserDTO>> getAllUser() {
    List<UserDTO> users = userService.getAllUser();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @Operation(
    description = "This EndPoint will Help you to Update a Existing User",
    summary = "This EndPoint Update  a Exisiting User Using userId and the data body"
  )
  //for updating a user
  @PutMapping("/{userId}/update")
  public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
      userService.updateUserByUserName(userId, userDTO);
      return new ResponseEntity<>("User Update successfully", HttpStatus.OK);
  }

  @Operation(
    description="Delete an existing user from the Database"
    ,summary="Delete the particular User by using UserID")
  //delete the user by userID
  @DeleteMapping("/{userId}/delete")
  public ResponseEntity<String> delete_User(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
  }
}
