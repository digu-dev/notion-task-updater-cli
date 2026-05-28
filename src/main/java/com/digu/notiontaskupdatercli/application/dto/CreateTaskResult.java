package com.digu.notiontaskupdatercli.application.dto;

import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.Task;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.util.UUID;

public record CreateTaskResult(UUID id, String title, TaskStatus status, SyncStatus syncStatus) {

    public static CreateTaskResult from(Task task) {
        return new CreateTaskResult(task.getId(), task.getTitle(), task.getStatus(), task.getSyncStatus());
    }
}
