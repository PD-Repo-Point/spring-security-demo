package pd.workshop.springsecuritydemoproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pd.workshop.springsecuritydemoproject.model.Employee;

import java.util.Arrays;
import java.util.List;

// EMPLOYEE
@RestController
@RequestMapping("api/v1/springsecurity/employee")
public class EmployeeController {

    private static final List<Employee> EMPLOYEE_LIST = Arrays.asList(
            new Employee(1, "Alexa", "IN"),
            new Employee(2, "Catalina", "USA"),
            new Employee(3, "Jasper", "ARG"));

    @GetMapping(path = "{empId}")
    public Employee getEmployee(@PathVariable("empId") Integer empId){
        return EMPLOYEE_LIST.stream()
                .filter(emp -> empId.equals(emp.getEmployeeId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Employee with "+empId + "doesn't exists."));
    }
// Form Based Authentication
// Basic Auth
// OAuth
// JWT
}
