package crud_operation.crud_operations.Service;

import java.util.List;

import crud_operation.crud_operations.DTO.UserDTO;

public interface UserService {
  UserDTO saveUser(UserDTO userDTO);
   UserDTO getUserByUserName(String userName) ;
   UserDTO getUserById(Long userId);
   List<UserDTO> getAllUser() ;
   void updateUserByUserName(Long userId, UserDTO userDTO);
   void deleteUser(Long userId);
}
