package pd.workshop.springsecuritydemoproject.repository;

import org.springframework.stereotype.Repository;
import pd.workshop.springsecuritydemoproject.model.ApplicationUser;

import java.util.Optional;

@Repository("redis")
public class ApplicationUserRedisImpl implements ApplicationUserDao{
    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return Optional.empty();
    }
}
