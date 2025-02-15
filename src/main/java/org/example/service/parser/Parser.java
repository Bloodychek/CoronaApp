package org.example.service.parser;

import org.example.model.Department;

import java.util.List;

public interface Parser {
    List<Department> parse(List<String> lines);

    List<String> getInvalidData();
}
