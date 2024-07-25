package crud_operation.crud_operations.Config;

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

@SecurityScheme(type = SecuritySchemeType.HTTP,name = "Bearer Authentication",bearerFormat = "JWT" ,scheme="bearer")
public class SwaggerConfig {


  @Bean
  public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi
      .builder()
      .group("Users")
      .pathsToMatch("/users/**")
      .build();
  }

  @Bean
  public GroupedOpenApi groupedOpenApiAuthentication() {
    return GroupedOpenApi
      .builder()
      .group("Authentication")
      .pathsToMatch("/auth/login/**")
      .build();
  }
  }
