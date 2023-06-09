package pd.workshop.springsecuritydemoproject.repository;

import pd.workshop.springsecuritydemoproject.model.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
