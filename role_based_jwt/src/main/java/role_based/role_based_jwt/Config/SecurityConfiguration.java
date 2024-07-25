package role_based.role_based_jwt.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import role_based.role_based_jwt.Entity.Role;
import role_based.role_based_jwt.Service.Implemention.UserServiceImpl;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  @Autowired
  private  JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private  UserServiceImpl UserServiceImpl;

  @Autowired
  private  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Autowired
  private  CustomAccessDeniedHandler customAccessDeniedHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(request ->
        request
          .requestMatchers(
            "/auth/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**"
          )
          .permitAll()
          .requestMatchers("/admin/**")
          .hasAnyAuthority(Role.ADMIN.name())
          .requestMatchers("/user/**")
          .hasAnyAuthority(Role.USER.name())
          .anyRequest()
          .authenticated()
      )
      .exceptionHandling(ex ->
        ex
          .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
          .accessDeniedHandler(this.customAccessDeniedHandler)
      )
      .sessionManagement(manager ->
        manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authenticationProvider(authenticationProvider()) 
      .addFilterBefore(
        jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class
      );
    return http.build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() throws Exception {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(UserServiceImpl);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    AuthenticationConfiguration config
  ) throws MethodArgumentNotValidException, Exception {
    return config.getAuthenticationManager();
  }
}
