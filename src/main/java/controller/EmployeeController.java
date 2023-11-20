package controller;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageFullException;
import impl.EmployeeServiceImpl;
import model.Employee;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> printEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/add")
    @ExceptionHandler({EmployeeAlreadyAddedException.class, EmployeeStorageFullException.class})
    public String add(@RequestParam("firstName") String firstName,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("salary") int salary,
                      @RequestParam("department") Integer department) {
        employeeService.add(firstName, lastName, department, salary);
        return "Employee added";
    }

    @GetMapping("/remove")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public String remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("salary") int salary,
                         @RequestParam("department") Integer department) {
        employeeService.remove(firstName, lastName, department, salary);
        return "Сотрудник с фио " + firstName + " " + lastName;
    }

    @GetMapping("/find")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("salary") int salary,
                         @RequestParam("department") Integer department) {
        return employeeService.find(firstName, lastName, department, salary);
    }

    @GetMapping("/departments/max-salary")
    public Optional<Employee> findMaxSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.MaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public Optional<Employee> findMinSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.MinSalaryInDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public List<Employee> returnEmployeeInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.EmployeeInDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> returnAllEmployeeInDepartment() {
        return employeeService.AllEmployeeInDepartment();
    }
}
