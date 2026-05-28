package com.digu.notiontaskupdatercli.application.dto;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDateTime;

public record CreateTaskCommand(
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        LocalDateTime dueDate) {

    public CreateTaskCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status is required.");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority is required.");
        }

        title = title.trim();
        description = normalizeOptional(description);
    }

    private static String normalizeOptional(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }
}
