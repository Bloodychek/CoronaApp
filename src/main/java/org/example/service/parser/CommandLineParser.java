package org.example.service.parser;

import lombok.Getter;
import org.example.util.Constants;
import org.example.util.ErrorMessages;

@Getter
public class CommandLineParser {

    private String sortBy;
    private String orderBy;
    private String output;
    private String outputPath;

    public CommandLineParser(String[] args) {
        for (String arg : args) {
            if (!arg.contains(Constants.ARGUMENT_DELIMITER) || arg.startsWith(Constants.ARGUMENT_DELIMITER)) {
                throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_ARGUMENT, arg));
            }

            String[] parts = arg.split("=", 2);
            String key = parts[0].replaceAll("^-{1,2}", "").toLowerCase();
            String value = parts[1].trim();

            switch (key) {
                case "sort", "s" -> {
                    if (!Constants.VALID_SORT_OPTIONS.contains(value.toLowerCase())) {
                        throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_SORT_VALUE, value));
                    }
                    sortBy = value;
                }
                case "order" -> {
                    if (!Constants.VALID_ORDER_OPTIONS.contains(value.toLowerCase())) {
                        throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_ORDER_VALUE, value));
                    }
                    orderBy = value;
                }
                case "output", "o" -> {
                    if (!Constants.VALID_OUTPUT_OPTIONS.contains(value.toLowerCase())) {
                        throw new IllegalArgumentException(String.format(ErrorMessages.INVALID_OUTPUT_VALUE, value));
                    }
                    output = value;
                }
                case "path" -> outputPath = value;
                default -> throw new IllegalArgumentException(String.format(ErrorMessages.UNKNOWN_ARGUMENT, key));
            }
        }
    }
}