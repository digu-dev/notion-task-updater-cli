package com.digu.notiontaskupdatercli.application.dto;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.Task;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskSummary(
        UUID id,
        String title,
        TaskStatus status,
        Priority priority,
        SyncStatus syncStatus,
        LocalDateTime dueDate) {

    public static TaskSummary from(Task task) {
        return new TaskSummary(
                task.getId(),
                task.getTitle(),
                task.getStatus(),
                task.getPriority(),
                task.getSyncStatus(),
                task.getDueDate());
    }
}
