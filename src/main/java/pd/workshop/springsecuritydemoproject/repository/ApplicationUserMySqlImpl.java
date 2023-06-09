package pd.workshop.springsecuritydemoproject.repository;

import org.springframework.stereotype.Repository;
import pd.workshop.springsecuritydemoproject.model.ApplicationUser;

import java.util.Optional;

@Repository("mysql")
public class ApplicationUserMySqlImpl implements ApplicationUserDao{
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return Optional.empty();
    }
}
