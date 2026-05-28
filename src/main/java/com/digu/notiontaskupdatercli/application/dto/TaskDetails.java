package com.digu.notiontaskupdatercli.application.dto;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.Task;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDetails(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        Priority priority,
        SyncStatus syncStatus,
        LocalDateTime dueDate,
        String notionPageId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public static TaskDetails from(Task task) {
        return new TaskDetails(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getSyncStatus(),
                task.getDueDate(),
                task.getNotionPageId(),
                task.getCreatedAt(),
                task.getUpdatedAt());
    }
}
