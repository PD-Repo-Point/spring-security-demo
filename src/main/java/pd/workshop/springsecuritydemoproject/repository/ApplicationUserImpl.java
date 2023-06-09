package pd.workshop.springsecuritydemoproject.repository;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import pd.workshop.springsecuritydemoproject.model.ApplicationUser;

import java.util.List;
import java.util.Optional;

import static pd.workshop.springsecuritydemoproject.security.AppUserRole.*;

@Repository("list")
public class ApplicationUserImpl implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
        return getApplicationUser().stream()
                .filter(applicationUser -> username.equalsIgnoreCase(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUser(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
             new ApplicationUser(
                     "alexa",
                     passwordEncoder.encode("password"),
                     EMPLOYEE.getGrantedAuthorities(),
                     true,
                     true,
                     true,
                     true),
             new ApplicationUser(
                     "tom",
                     passwordEncoder.encode("password"),
                     ADMIN.getGrantedAuthorities(),
                     true,
                     true,
                     true,
                     true
             ),
                new ApplicationUser(
                        "ayesha",
                        passwordEncoder.encode("password"),
                        ADMIN_TRAINEE.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;

    }

}
