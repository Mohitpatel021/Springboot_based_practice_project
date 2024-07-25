package requestbody_and_responsebody.requestbody_and_requestresponse.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import requestbody_and_responsebody.requestbody_and_requestresponse.DTO.RequestBodyDTO;
import requestbody_and_responsebody.requestbody_and_requestresponse.Entity.User;
import requestbody_and_responsebody.requestbody_and_requestresponse.Repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  //Get the user by username
  public RequestBodyDTO getUserByUsername(String userName) {
    User user = userRepository.findByUserName(userName);
    return new RequestBodyDTO(user);
  }

  //Get user by using userID
  public RequestBodyDTO getUserById(Long userID) {
    User user = userRepository
      .findById(userID)
      .orElseThrow(() ->
        new EntityNotFoundException("User Not found with this entity")
      );
    return new RequestBodyDTO(user);
  }

  //Save a new user
  public RequestBodyDTO saveUser(RequestBodyDTO requestBodyDTO) {
    System.out.println("service method is calling "+requestBodyDTO.getUserName());
    User user = requestBodyDTO.toEntity(requestBodyDTO);
    User savedUser = userRepository.save(user);
    return new RequestBodyDTO(savedUser);
  }
}
