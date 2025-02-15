package org.example.service.sorter;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.util.Constants;

import java.util.Comparator;
import java.util.List;

public class EmployeeSorter implements Sorter {
    @Override
    public void sort(List<Department> departments, String sortBy, String sortOrder) {
        Comparator<Employee> comparator = getComparator(sortBy);
        if (comparator == null) return;

        if (Constants.ORDER_DESC.equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        Comparator<Employee> finalComparator = comparator;
        departments.forEach(dep -> dep.getEmployees().sort(finalComparator));
    }

    private Comparator<Employee> getComparator(String sortBy) {
        if (Constants.SORT_NAME.equalsIgnoreCase(sortBy)) {
            return Comparator.comparing(Employee::getName);
        }
        if (Constants.SORT_SALARY.equalsIgnoreCase(sortBy)) {
            return Comparator.comparingDouble(Employee::getSalary);
        }
        return null;
    }
}