package impl;

import exception.EmployeeNotFoundException;
import exception.EmployeeStorageFullException;
import exception.EmployeeAlreadyAddedException;
import model.Employee;
import org.springframework.stereotype.Service;
import service.EmployeeService;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    public static final int MAX_EMPLOYEES = 5;
    List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }

    @Override
        public void add(String firstName, String lastName, Integer salary, Integer department) {
            Employee requestedEmployee = new Employee(firstName, lastName, salary, department);

            if (employees.size() >= MAX_EMPLOYEES) {
                throw new EmployeeStorageFullException("Хранилище заполнено!");
            }
            if (employees.contains(requestedEmployee)){
                throw new EmployeeAlreadyAddedException("Сотрудник уже был добавлен в хранилище!");
            }
            employees.add(requestedEmployee);
        }

    @Override
    public void remove(String firstName, String lastName, Integer salary, Integer department) {
        Employee requestedEmployee = new Employee(firstName, lastName, salary, department);
        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник с такими данными не найден!");
        }
        employees.remove(requestedEmployee);
    }

    @Override
    public Employee find(String firstName, String lastName, Integer salary, Integer department) {
        Employee requestedEmployee = new Employee(firstName, lastName, salary, department);
        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException("Сотрудник с такими данными не найден!");
        }
        throw new EmployeeNotFoundException("Сотрудник с такими данными не найден!");
    }

    @Override
    public Optional<Employee> MaxSalaryInDepartment(Integer department) {
        return employees.stream()

        .filter(e -> e.getDepartment().equals(department))
        .max(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Optional<Employee> MinSalaryInDepartment(Integer department) {
        return employees.stream()
        .filter(e -> e.getDepartment().equals(department))
        .min(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public List<Employee> EmployeeInDepartment(Integer department) {
        return employees.stream()
        .filter(e -> e.getDepartment().equals(department))
        .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> AllEmployeeInDepartment() {
        return employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
        Collectors.mapping(e -> e,
        Collectors.toList())));
    }
}