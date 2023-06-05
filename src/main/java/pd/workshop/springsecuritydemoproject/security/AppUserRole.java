package pd.workshop.springsecuritydemoproject.security;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static pd.workshop.springsecuritydemoproject.security.AppUserPermission.*;

public enum AppUserRole {
    EMPLOYEE(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            EMPLOYEE_READ,
            EMPLOYEE_WRITE));

    private final Set<AppUserPermission> permissions;
    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions=permissions;
    }
}
