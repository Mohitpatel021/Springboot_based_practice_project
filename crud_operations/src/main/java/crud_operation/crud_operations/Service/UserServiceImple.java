package crud_operation.crud_operations.Service;

import crud_operation.crud_operations.DTO.UserDTO;
import crud_operation.crud_operations.Exception.UserNotFoundExceptions;
import crud_operation.crud_operations.Model.User;
import crud_operation.crud_operations.Repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImple implements UserService {

  @Autowired
  private UserRepository userRepository;

  
  //For saving a user
  @Override
  public UserDTO saveUser(UserDTO userDTO) {
    System.out.println(
      "Service method called for the userName " + userDTO.getUserName()
    );
    User user = userDTO.toEntity(userDTO);

    System.out.println(
      "service method called for the userName " + user.getUserName()
    );

    return new UserDTO(userRepository.save(user));
  }


  //Get User BY UserName
  @Override
  public UserDTO getUserByUserName(String userName) {
    System.out.println("service method called" + userName);
    User user = userRepository
      .findByUserName(userName)
      .orElseThrow(() ->
        new UserNotFoundExceptions("User Not found with this userName")
      );
    return new UserDTO(user);
  }


  //Get User By UserID
  @Override
  public UserDTO getUserById(Long userId) {
    User user = userRepository
      .findById(userId)
      .orElseThrow(() ->
        new UserNotFoundExceptions("User With this User ID is Not Found")
      );
    return new UserDTO(user);
  }

  //Fetch all the user
  @Override
  public List<UserDTO> getAllUser() {
    Iterable<User> usersIterable = userRepository.findAll();

    List<User> users = new ArrayList<>();
    usersIterable.forEach(users::add);

    List<UserDTO> userDTOs = new ArrayList<>();
    for (User user : users) {
      userDTOs.add(converToDTO(user));
    }
    return userDTOs;
  }

  //update user by username
  @Override
  public void updateUserByUserName(Long userId, UserDTO userDTO) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      if (userDTO.getPhoneNumber() != null) {
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
      }
      if (userDTO.getUserName() != null) {
        existingUser.setUserName(userDTO.getUserName());
      }
      if (userDTO.getEmail() != null) {
        existingUser.setEmail(userDTO.getEmail());
      }
      userRepository.save(existingUser);
      // return convertToDTO(existingUser);
    } else {
      throw new UserNotFoundExceptions("User Not found with this userID");
    }
  }

  //For deleting the user by userId
  @Override
  public void deleteUser(Long userId) {
    Optional<User> user = userRepository.findById(userId);
    if (user != null) {
      userRepository.deleteById(userId);
    } else {
      throw new UserNotFoundExceptions("User Not found with this userID");
    }
  }

  //this is use for converting the user object to UserDTo object
  private UserDTO converToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setUserName(user.getUserName());
    userDTO.setPhoneNumber(user.getPhoneNumber());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }
}
