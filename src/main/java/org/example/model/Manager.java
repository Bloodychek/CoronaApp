package org.example.model;

import lombok.Getter;

@Getter
public class Manager extends Person {
    private final String department;

    public Manager(String id, String name, double salary, String department) {
        super(id, name, salary);
        this.department = department;
    }
}