package model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private Integer salary;
    private Integer department;
    private static int counter = 0;
    private final int id;

    public Employee(String firstName, String lastName, Integer salary, Integer department) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
            this.department = department;
            this.id = ++counter;
    }

    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public Integer getSalary() {return salary;}
    public Integer getDepartment() {return department;}
    public int getId() {return id;}

    @Override
    public String toString() {
        return "Employee{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", id=" + id + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, id);
    }
}