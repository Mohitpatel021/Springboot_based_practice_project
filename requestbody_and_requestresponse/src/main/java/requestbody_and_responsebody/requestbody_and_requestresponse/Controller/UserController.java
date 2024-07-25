package requestbody_and_responsebody.requestbody_and_requestresponse.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import requestbody_and_responsebody.requestbody_and_requestresponse.DTO.RequestBodyDTO;
import requestbody_and_responsebody.requestbody_and_requestresponse.Service.UserService;

@RestController
@RequestMapping(path = "/user")
@Tag(
  name = "User",
  description = "This is the User Controller and responsible for handling a User"
)
public class UserController {

  @Autowired
  private UserService userService;

  @Operation(
    description = "The Get endPoint for Fetching a User Profile By Username",
    summary = "This is the User profile EndPoint"
  )
  @GetMapping("/profile")
  public ResponseEntity<RequestBodyDTO> getUserByUsername(
    @RequestParam String userName
  ) {
    RequestBodyDTO fetchedUserDTO = userService.getUserByUsername(userName);

    return new ResponseEntity<>(fetchedUserDTO, HttpStatus.OK);
  }

  @Operation(
    description = "This is the Post endPoint for Saving a New User",
    summary = "Create a new User in the Database"
  )
  @PostMapping(path = "/save")
  public RequestBodyDTO saveUser(
    @Valid @RequestBody RequestBodyDTO requestDto
  ) {
    System.out.println(
      "controller method is called for saving a user " +
      requestDto.getUserName()
    );
    RequestBodyDTO newUserDto = userService.saveUser(requestDto);
    return newUserDto;
  }

  @Operation(
    description = "This is the Get endPoint for fetching a user by their ID",
    summary = "Fetch a User By UserID"
  )
  @GetMapping("/{userID}")
  public ResponseEntity<RequestBodyDTO> getUserById(@PathVariable Long userID) {
    RequestBodyDTO fetchedUserDto = userService.getUserById(userID);
    return new ResponseEntity<>(fetchedUserDto, HttpStatus.OK);
  }
}
