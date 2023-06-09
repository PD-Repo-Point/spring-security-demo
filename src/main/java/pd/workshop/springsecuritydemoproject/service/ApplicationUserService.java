package pd.workshop.springsecuritydemoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pd.workshop.springsecuritydemoproject.repository.ApplicationUserDao;

@Service
public class ApplicationUserService implements UserDetailsService {
    private final ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserService(@Qualifier("list")
                                      ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao
                .selectApplicationUserByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("Username : %s not found.",username)
                ));
    }
}
