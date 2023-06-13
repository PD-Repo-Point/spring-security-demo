package pd.workshop.springsecuritydemoproject.auth.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {
    // OncePerRequestFilter : To be executed once per request
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String secretKey =
                "secretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

        String authorizationHeader = request.getHeader("Authorization");
        /*
        * Bearer eyJhbGciOiJIUzUxMiJ9.
        * eyJzdWIiOiJhbGV4YSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0VNUEx
        * PWUVFIn1dLCJpYXQiOjE2ODY2MzU3MTMsImV4cCI6MTY4NzE5OTQwMH0.
        * rN6-pvI59Zi1lQVTsCaDuIjX-ZaILq4_39ggXqzbMz7G_7gNrkISKa25mRBYUci-
        * cwcGNsTQqrOW0qpPlieR0w
        *
        * Bearer Token
        * */

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        try {

            String token = authorizationHeader.replace("Bearer ", "");
            /* TOKEN --->
             * eyJhbGciOiJIUzUxMiJ9.
             * eyJzdWIiOiJhbGV4YSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0VNUEx
             * PWUVFIn1dLCJpYXQiOjE2ODY2MzU3MTMsImV4cCI6MTY4NzE5OTQwMH0.
             * rN6-pvI59Zi1lQVTsCaDuIjX-ZaILq4_39ggXqzbMz7G_7gNrkISKa25mRBYUci-
             * cwcGNsTQqrOW0qpPlieR0w
             * */

            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())) // secret key
                    .parseClaimsJws(token);// token
            // Jws - Json Web Signature

            Claims body = claimsJws.getBody();

            String username = body.getSubject();
        /*
        * "authorities": [
            {
              "authority": "employee:write"
            },
            {
              "authority": "course:read"
            },
            {
              "authority": "ROLE_ADMIN"
            },
            {
              "authority": "employee:read"
            },
            {
              "authority": "course:write"
            }
          ]
        *
        * */

            //List<Map<String, String>>
            var authorities = (List<Map<String, String>>) body.get("authorities");

            // authorities ---> Granted Authorities ---> SimpleGrantedAuthorities

            Set<SimpleGrantedAuthority> simpleGrantedAuthoritiesSet
                    = authorities.stream()
                    .map(a ->
                            new SimpleGrantedAuthority(a.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            simpleGrantedAuthoritiesSet);
            // SecurityContextHolder
            // At the heart of Spring Security’s authentication model is the SecurityContextHolder.
            // It contains the SecurityContext.

            // URL : https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-securitycontextholder
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(JwtException ex){
            throw new IllegalStateException("Token cannot be trusted");
        }
        filterChain.doFilter(request,response);

    }
}
