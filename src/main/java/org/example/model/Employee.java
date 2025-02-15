package org.example.model;

import lombok.Getter;

@Getter
public class Employee extends Person {
    private final String managerId;

    public Employee(String id, String name, double salary, String managerId) {
        super(id, name, salary);
        this.managerId = managerId;
    }
}