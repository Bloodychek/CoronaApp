package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Department implements Comparable<Department> {
    private final String name;
    private Manager manager;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.manager = null;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    @Override
    public int compareTo(Department o) {
        return this.name.compareTo(o.name);
    }
}