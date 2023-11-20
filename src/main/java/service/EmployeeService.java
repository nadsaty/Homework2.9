package service;

import model.Employee;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    void add(String firstName, String lastName, Integer salary, Integer department);
    void remove(String firstName, String lastName, Integer salary, Integer department);
    Employee find(String firstName, String lastName, Integer salary, Integer department);

    Optional<Employee> MaxSalaryInDepartment(Integer department);
    Optional<Employee> MinSalaryInDepartment(Integer department);


    List<Employee> EmployeeInDepartment(Integer department);
    Map<Integer, List<Employee>> AllEmployeeInDepartment();
}