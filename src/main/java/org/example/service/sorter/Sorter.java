package org.example.service.sorter;

import org.example.model.Department;

import java.util.List;

public interface Sorter {
    void sort(List<Department> departments, String sortBy, String orderBy);
}
