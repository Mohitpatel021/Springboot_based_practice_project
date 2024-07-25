package role_based.role_based_jwt.Service.Implemention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import role_based.role_based_jwt.Exception.UserNotFoundExceptions;
import role_based.role_based_jwt.Repository.UserRepository;

@Service
public class  UserServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository
      .findByEmail(username)
      .orElseThrow(() ->
        new UserNotFoundExceptions("Please Check Your UserName!!")
      );
  }
}
