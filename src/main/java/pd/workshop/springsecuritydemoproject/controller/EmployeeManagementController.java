package pd.workshop.springsecuritydemoproject.controller;

import org.springframework.web.bind.annotation.*;
import pd.workshop.springsecuritydemoproject.model.Employee;

import java.util.Arrays;
import java.util.List;
// ADMIN - ADMIN_TRAINEE
@RestController
@RequestMapping("management/api/v1/springsecurity/employee")
public class EmployeeManagementController {
    private static final List<Employee> EMPLOYEE_LIST = Arrays.asList(
            new Employee(1, "Alexa", "IN"),
            new Employee(2, "Catalina", "USA"),
            new Employee(3, "Jasper", "ARG"));

    //GET POST PUT DELETE

    @GetMapping
    public List<Employee> getAllEmployees(){return EMPLOYEE_LIST;}

    @PostMapping
    public void registerNewEmployee(@RequestBody Employee employee){
        System.out.println("Employee with "+ employee.getEmployeeId() +" is registered");
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") Integer id){
        System.out.println("Employee with "+ id +" is deleted");
    }

    @PutMapping(path = "{id}")
    public void updateEmployee(@PathVariable("id") Integer id,
                               @RequestBody Employee employee){
        System.out.println("Employee with "+ id + " has been updated");
    }
}
