package impl;

import exception.EmployeeNotFoundException;
import exception.EmployeeStorageFullException;
import exception.EmployeeAlreadyAddedException;
import model.Employee;
import org.springframework.stereotype.Service;
import service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private final int STORAGE_SIZE = 5;

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageFullException("Хранилище сотрудников заполнено!");
        }
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник " + firstName + " " + lastName + " уже добавлен в хранилище!");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден!");
        }
        return employees.remove(getKey(firstName, lastName));
    }
    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName + " не найден!");
        }
        return employee;
    }
    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
