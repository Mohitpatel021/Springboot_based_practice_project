package requestbody_and_responsebody.requestbody_and_requestresponse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import requestbody_and_responsebody.requestbody_and_requestresponse.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}

