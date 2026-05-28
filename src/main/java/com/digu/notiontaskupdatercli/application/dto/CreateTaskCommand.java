package com.digu.notiontaskupdatercli.application.dto;

import com.digu.notiontaskupdatercli.domain.model.Priority;

import java.time.LocalDateTime;

public record CreateTaskCommand(
        String title,
        String description,
        Priority priority,
        LocalDateTime dueDate
) {
}
