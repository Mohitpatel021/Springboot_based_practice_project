package role_based.role_based_jwt.DTO;

public class JwtAuthenticationResponse {
    
    private String token;

    private String email;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
