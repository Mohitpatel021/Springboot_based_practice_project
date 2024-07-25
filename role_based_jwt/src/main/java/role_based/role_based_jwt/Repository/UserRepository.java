package role_based.role_based_jwt.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import role_based.role_based_jwt.Entity.Role;
import role_based.role_based_jwt.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByFirstName(String firstName);
    User findByRole(Role role);
}
