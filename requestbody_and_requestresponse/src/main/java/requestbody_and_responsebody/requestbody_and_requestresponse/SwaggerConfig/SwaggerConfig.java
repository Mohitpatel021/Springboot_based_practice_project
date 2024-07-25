package requestbody_and_responsebody.requestbody_and_requestresponse.SwaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
  info = @Info(
    contact = @Contact(name = "High Tech", email = "hightech@gmail.com"),
    description = "OpenAPI Documentation for Integrating Swagger",
    title = "OpenAPI Specification-High Tech Infotech",
    version = "2.2.0",
    license = @License(name = "License_name", url = "https://some-url.com"),
    termsOfService = "Terms Of Service"
  
  ),
  servers = {
    @Server(description = "Local ENV", url = "http://Localhost:8080"),
  }
)
@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi groupedOpenApi() {
    return GroupedOpenApi.builder()
    .group("User")
    .pathsToMatch("/user/**")
    .build();
  }
}
