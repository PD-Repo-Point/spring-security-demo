package pd.workshop.springsecuritydemoproject.security;

public enum AppUserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");
    private final String permission;
    AppUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission(){
        return permission;
    }
}
