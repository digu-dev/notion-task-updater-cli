package com.digu.notiontaskupdatercli.adapter.in.cli;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TaskCliInputParser {

    public String parseTitle(String rawTitle) {
        if (rawTitle == null || rawTitle.isBlank()) {
            throw new CliValidationException("Title is required.");
        }

        return rawTitle.trim();
    }

    public TaskStatus parseStatus(String rawStatus) {
        return parseEnum(rawStatus, TaskStatus.class, "status");
    }

    public Priority parsePriority(String rawPriority) {
        return parseEnum(rawPriority, Priority.class, "priority");
    }

    public LocalDateTime parseDueDate(String rawDueDate) {
        if (rawDueDate == null || rawDueDate.isBlank()) {
            return null;
        }

        String value = rawDueDate.trim();
        try {
            return LocalDateTime.parse(value);
        } catch (DateTimeParseException ignored) {
            try {
                return LocalDate.parse(value).atStartOfDay();
            } catch (DateTimeParseException exception) {
                throw new CliValidationException("Invalid due date. Use yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss.");
            }
        }
    }

    public UUID parseUuid(String rawId) {
        if (rawId == null || rawId.isBlank()) {
            throw new CliValidationException("Invalid task id. Use a valid UUID.");
        }

        try {
            return UUID.fromString(rawId.trim());
        } catch (IllegalArgumentException exception) {
            throw new CliValidationException("Invalid task id. Use a valid UUID.");
        }
    }

    private <E extends Enum<E>> E parseEnum(String rawValue, Class<E> enumType, String fieldName) {
        if (rawValue == null || rawValue.isBlank()) {
            throw new CliValidationException(capitalize(fieldName) + " is required.");
        }

        String normalizedValue = rawValue.trim().toUpperCase().replace('-', '_').replace(' ', '_');
        try {
            return Enum.valueOf(enumType, normalizedValue);
        } catch (IllegalArgumentException exception) {
            throw new CliValidationException(
                    "Invalid " + fieldName + " '" + rawValue.trim() + "'. Allowed values: "
                            + allowedValues(enumType) + ".");
        }
    }

    private <E extends Enum<E>> String allowedValues(Class<E> enumType) {
        return Arrays.stream(enumType.getEnumConstants()).map(Enum::name).collect(Collectors.joining(", "));
    }

    private String capitalize(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }
}
