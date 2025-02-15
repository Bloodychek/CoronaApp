package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Person {
    private final String id;
    private final String name;
    private final double salary;
}