package org.example.util;

public class ErrorMessages {
    public static final String INVALID_ARGUMENT = "Некорректный аргумент: %s";
    public static final String MANAGER_NOT_ASSIGNED = "Менеджер не назначен";
    public static final String INVALID_DATA = "Некорректные данные: ";
    public static final String UNKNOWN_ARGUMENT = "Неизвестный аргумент: %s";
    public static final String INVALID_SORT_VALUE = "Некорректное значение для --sort: %s. Допустимые значения: name и salary";
    public static final String INVALID_ORDER_VALUE = "Некорректное значение для --order: %s. Допустимые значения: asc и desc";
    public static final String INVALID_OUTPUT_VALUE = "Некорректное значение для --output: %s. Допустимые значения: console и file";
    public static final String MISSING_ORDER_FOR_SORT = "Параметр --order обязателен при указании --sort";
    public static final String MISSING_PATH_FOR_FILE_OUTPUT = "Для --output=file требуется указать --path";
    public static final String FILE_READ_ERROR = "Ошибка чтения файла: %s";
}