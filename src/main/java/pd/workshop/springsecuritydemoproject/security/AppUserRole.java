package pd.workshop.springsecuritydemoproject.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static pd.workshop.springsecuritydemoproject.security.AppUserPermission.*;

public enum AppUserRole {
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            EMPLOYEE_READ,
            EMPLOYEE_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(
            COURSE_READ,
            EMPLOYEE_READ
            ));

    private final Set<AppUserPermission> permissions;
    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions=permissions;
    }
    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    // For a particular role
    // Set-> {COURSE_READ, COURSE_WRITE, .... } ------> Set<SimpleGrantedAuthority>

    // Collection<? extends GrantedAuthority>
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority>   permissionSet
                = getPermissions()
                        .stream()
                        .map(permission ->
                                new SimpleGrantedAuthority(permission.getPermission()))
                        .collect(Collectors.toSet());

        permissionSet.add(
                new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissionSet;
    }
}
