package role_based.role_based_jwt.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(name = "High Tech", email = "hightech@infoTech.com"),
    description = "Role Based JWT Authentication and Authorization",
    title = "JWT Implementation",
    version = "2.2.0",
    license = @License(name = "License_Name", url = "https://some-url.com"),
    termsOfService = "Terms of Services"
  ),
  servers = {
    @Server(description = "Local ENV", url = "http://Localhost:8080"),
  }
)
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "Bearer"
)
public class SwaggerConfig {
  
  @Bean
  public GroupedOpenApi groupedOpenApi(){
    return GroupedOpenApi
    .builder()
    .group("Admin")
    .pathsToMatch("/admin/**")
    .build();
  }
  @Bean
  public GroupedOpenApi groupedOpenApi2(){
    return GroupedOpenApi
    .builder()
    .group("User")
    .pathsToMatch("/user/**")
    .build();
  }
  @Bean
  public GroupedOpenApi groupedOpenApi3(){
    return GroupedOpenApi
    .builder()
    .group("Authentication")
    .pathsToMatch("/auth/**")
    .build();
  }

}
