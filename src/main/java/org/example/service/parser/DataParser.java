package org.example.service.parser;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.model.Manager;
import org.example.util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataParser implements Parser {
    private final List<String> invalidData = new ArrayList<>();
    private final Map<String, Department> departments = new HashMap<>();
    private final Map<String, Manager> managers = new HashMap<>();
    private final Set<String> uniqueIds = new HashSet<>();

    @Override
    public List<Department> parse(List<String> lines) {
        for (String line : lines) {
            processLine(line.trim());
        }
        return new ArrayList<>(departments.values());
    }

    private void processLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length != Constants.EXPECTED_FIELDS) {
            invalidData.add(line);
            return;
        }

        String role = parts[0].trim();
        String id = parts[1].trim();
        String name = parts[2].trim();
        String salaryStr = parts[3].trim();
        String department = parts[4].trim();

        if (!uniqueIds.add(id)) {
            invalidData.add(line);
            return;
        }

        try {
            double salary = new BigDecimal(salaryStr).setScale(Constants.SCALE, RoundingMode.CEILING).doubleValue();
            if (salary <= 0) throw new NumberFormatException();

            if (role.equalsIgnoreCase(Constants.ROLE_MANAGER)) {
                processManager(id, name, salary, department);
            } else if (role.equalsIgnoreCase(Constants.ROLE_EMPLOYEE)) {
                processEmployee(id, name, salary, department);
            } else {
                invalidData.add(line);
            }
        } catch (NumberFormatException e) {
            invalidData.add(line);
        }
    }

    private void processManager(String id, String name, double salary, String department) {
        department = department.trim();
        Department dep = departments.computeIfAbsent(department, Department::new);
        Manager manager = new Manager(id, name, salary, department);
        dep.setManager(manager);
        managers.put(id, manager);
    }

    private void processEmployee(String id, String name, double salary, String managerId) {
        managerId = managerId.trim();
        Manager manager = managers.get(managerId);
        if (manager == null) {
            invalidData.add(String.format("Employee,%s,%s,%.2f,%s", id, name, salary, managerId));
            return;
        }
        Department dep = departments.computeIfAbsent(manager.getDepartment(), Department::new);
        dep.addEmployee(new Employee(id, name, salary, dep.getName()));
    }

    @Override
    public List<String> getInvalidData() {
        return invalidData;
    }
}
