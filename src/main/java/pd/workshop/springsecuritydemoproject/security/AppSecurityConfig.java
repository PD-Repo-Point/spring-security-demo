package pd.workshop.springsecuritydemoproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static pd.workshop.springsecuritydemoproject.security.AppUserPermission.COURSE_WRITE;
import static pd.workshop.springsecuritydemoproject.security.AppUserRole.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. ROLE BASED AUTHENTICATION
        // 2. PERMISSION BASED AUTHENTICATION
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index","/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(EMPLOYEE.name())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService()  {
        UserDetails alexaUser = User.builder()
                .username("alexa")
                .password(passwordEncoder.encode("password"))
                //.roles(EMPLOYEE.name()) // ROLE_EMPLOYEE
                .authorities(EMPLOYEE.getGrantedAuthorities())
                .build();

        UserDetails tomUser = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password123"))
                //.roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails ayeshaUser = User.builder()
                .username("ayesha")
                .password(passwordEncoder.encode("password1234"))
                //.roles(ADMIN_TRAINEE.name()) // ROLE_ADMIN_TRAINEE
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                alexaUser,
                tomUser,
                ayeshaUser);

        // Username     Password        Roles
        // ayesha       xy34zna@134     ROLE_ADMIN_TRAINEE
    }
}
