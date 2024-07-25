package role_based.role_based_jwt.Config;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import role_based.role_based_jwt.Exception.UserNotFoundExceptions;
import role_based.role_based_jwt.Service.Implemention.UserServiceImpl;
import role_based.role_based_jwt.Service.JWTService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

  @Autowired
  private  JWTService jwtService;

  @Autowired
  private  UserServiceImpl userServiceImpl;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws IOException, ServletException {
   
    final String authHeader = request.getHeader("Authorization");
    String jwt = null;
    String userEmail = null;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    } else {
      jwt = authHeader.substring(7); // "Bearer " has length 7
      try {
        userEmail = jwtService.extractUserName(jwt);
      } catch (ExpiredJwtException e) {
        logger.error("Jwt Token is Expired!!");
        e.printStackTrace();
      } catch (UserNotFoundExceptions e) {
        logger.error("User Not Found in Database with this token: {}");
        e.printStackTrace();
      } catch (Exception e) {
        logger.error("Error !! While Parsing the token:{}");
        // e.printStackTrace();
      }
    }
    if (
      userEmail != null &&
      SecurityContextHolder.getContext().getAuthentication() == null
    ) {
      try {
        UserDetails userDetails = userServiceImpl.loadUserByUsername(userEmail);
        if (jwtService.isTokenValid(jwt, userDetails)) {
          SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
          UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
          );
          token.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
          );
          securityContext.setAuthentication(token);
          SecurityContextHolder.setContext(securityContext);
        }
      } catch (UserNotFoundExceptions e) {
        e.printStackTrace();
      }
    }
    filterChain.doFilter(request, response);

  }
}
