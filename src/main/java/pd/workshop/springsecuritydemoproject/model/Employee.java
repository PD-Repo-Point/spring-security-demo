package pd.workshop.springsecuritydemoproject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private final Integer employeeId;
    private final String employeeName;
    private final String country;

    public Employee(Integer employeeId, String employeeName, String country) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.country = country;
    }
}
