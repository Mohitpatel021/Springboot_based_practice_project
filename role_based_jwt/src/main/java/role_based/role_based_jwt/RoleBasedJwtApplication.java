package role_based.role_based_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class RoleBasedJwtApplication  {
  public static void main(String[] args) {
    SpringApplication.run(RoleBasedJwtApplication.class, args);
  }
}
