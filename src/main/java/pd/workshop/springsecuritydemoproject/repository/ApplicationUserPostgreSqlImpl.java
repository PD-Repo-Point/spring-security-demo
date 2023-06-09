package pd.workshop.springsecuritydemoproject.repository;

import org.springframework.stereotype.Repository;
import pd.workshop.springsecuritydemoproject.model.ApplicationUser;

import java.util.Optional;

@Repository("psql")
public class ApplicationUserPostgreSqlImpl implements ApplicationUserDao{
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return Optional.empty();
    }
}
