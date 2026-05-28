package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.dto.TaskSummary;
import com.digu.notiontaskupdatercli.application.port.in.ListTasksUseCase;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import java.util.List;
import java.util.Objects;

public class ListTasksService implements ListTasksUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public ListTasksService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = Objects.requireNonNull(taskRepositoryPort, "Task repository is required.");
    }

    @Override
    public List<TaskSummary> listTasks() {
        return taskRepositoryPort.findAll().stream().map(TaskSummary::from).toList();
    }
}
