package pd.workshop.springsecuritydemoproject.auth.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsernameAndPasswordRequest {
    private String username;
    private String password;
}
