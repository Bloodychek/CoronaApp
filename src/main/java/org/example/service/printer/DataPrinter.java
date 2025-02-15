package org.example.service.printer;

import org.example.model.Department;
import org.example.model.Person;
import org.example.util.Constants;
import org.example.util.ErrorMessages;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class DataPrinter implements Printer {

    @Override
    public void printToConsole(List<Department> departments, List<String> invalidData) {
        print(departments, invalidData, System.out::println);
    }

    @Override
    public void printToFile(List<Department> departments, List<String> invalidData, String path) throws IOException {
        List<String> lines = new ArrayList<>();
        print(departments, invalidData, lines::add);
        Files.write(Paths.get(path), lines);
    }

    private void print(List<Department> departments, List<String> invalidData, Consumer<String> output) {
        departments.stream().sorted().forEach(dep -> printDepartment(dep, output));

        if (!invalidData.isEmpty()) {
            output.accept(ErrorMessages.INVALID_DATA);
            invalidData.forEach(output);
        }
    }

    private void printDepartment(Department dep, Consumer<String> output) {
        output.accept(dep.getName());

        int employeeCount = dep.getEmployeeCount();
        double totalSalary = dep.getEmployees().stream().mapToDouble(Person::getSalary).sum();

        if (dep.getManager() != null) {
            employeeCount++;
            totalSalary += dep.getManager().getSalary();
            output.accept(String.format(Locale.US, "Manager,%s,%s,%.2f",
                    dep.getManager().getId(),
                    dep.getManager().getName(),
                    dep.getManager().getSalary()));
        } else {
            output.accept(ErrorMessages.MANAGER_NOT_ASSIGNED);
        }

        dep.getEmployees().forEach(e -> output.accept(String.format(Locale.US, "Employee,%s,%s,%.2f",
                e.getId(), e.getName(), e.getSalary())));

        double avgSalary = employeeCount > 0 ? BigDecimal.valueOf(totalSalary / employeeCount).setScale(Constants.SCALE,
                RoundingMode.CEILING).doubleValue() : Constants.DEFAULT_AVG_SALARY;
        output.accept(String.format(Locale.US, "%d,%.2f", employeeCount, avgSalary));
        output.accept("");
    }
}