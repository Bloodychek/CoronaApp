package org.example.util;

import java.util.Set;

public class Constants {
    public static final String SORT_NAME = "name";
    public static final String SORT_SALARY = "salary";
    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";
    public static final Set<String> VALID_SORT_OPTIONS = Set.of(SORT_NAME, SORT_SALARY);
    public static final Set<String> VALID_ORDER_OPTIONS = Set.of(ORDER_ASC, ORDER_DESC);

    public static final String ROLE_MANAGER = "manager";
    public static final String ROLE_EMPLOYEE = "employee";

    public static final String OUTPUT_CONSOLE = "console";
    public static final String OUTPUT_FILE = "file";
    public static final Set<String> VALID_OUTPUT_OPTIONS = Set.of(OUTPUT_CONSOLE, OUTPUT_FILE);

    public static final String INPUT_FILE = "src/main/resources/input.txt";
    public static final String ARGUMENT_DELIMITER = "=";

    public static final int EXPECTED_FIELDS = 5;
    public static final int SCALE = 2;
    public static final double DEFAULT_AVG_SALARY = 0.00;
}
