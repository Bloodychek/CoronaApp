package org.example.service.printer;

import org.example.model.Department;

import java.io.IOException;
import java.util.List;

public interface Printer {
    void printToConsole(List<Department> departments, List<String> invalidData);

    void printToFile(List<Department> departments, List<String> invalidData, String filePath) throws IOException;
}
