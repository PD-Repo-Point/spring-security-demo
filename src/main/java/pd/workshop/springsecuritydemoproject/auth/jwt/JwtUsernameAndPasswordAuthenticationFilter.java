package pd.workshop.springsecuritydemoproject.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    // The server receives the credentials send by the client
    // the server with AuthenticationManager then validates the credentials and authenticate

    // Authentication - Can be the input to AuthenticationManager
    // to provide the credentials a user has provided to authenticate


    // AuthenticationManager - the API that defines how Spring Security’s Filters perform authentication.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {

            UsernameAndPasswordRequest authenticateRequest = new ObjectMapper().readValue(
                    request.getInputStream(),
                    UsernameAndPasswordRequest.class);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            authenticateRequest.getUsername(),
                            authenticateRequest.getPassword());

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 1. This method will only be invoked after the attemptAuthentication is success
    // 2. Method is to generate the token that is to be sent by the server to the client

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String secretKey =
                "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer "+token);

    }

}
