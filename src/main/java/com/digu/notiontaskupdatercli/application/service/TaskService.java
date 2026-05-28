package com.digu.notiontaskupdatercli.application.service;

import com.digu.notiontaskupdatercli.application.command.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.usecase.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.usecase.FindTaskByIdUseCase;
import com.digu.notiontaskupdatercli.application.usecase.ListTasksUseCase;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.Task;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import com.digu.notiontaskupdatercli.domain.port.TaskRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService implements CreateTaskUseCase, ListTasksUseCase, FindTaskByIdUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task execute(CreateTaskCommand command) {
        Task task = Task.builder()
                .title(command.getTitle())
                .type(command.getType())
                .category(command.getCategory())
                .subcategory(command.getSubcategory())
                .tags(command.getTags())
                .horizon(command.getHorizon())
                .priority(command.getPriority())
                .importance(command.getImportance())
                .objective(command.getObjective())
                .severity(command.getSeverity())
                .ownerToDo(command.getOwnerToDo())
                .status(command.getStatus() != null ? command.getStatus() : TaskStatus.NOT_STARTED)
                .description(command.getDescription())
                .estimatedHours(command.getEstimatedHours())
                .startDate(command.getStartDate())
                .dueDate(command.getDueDate())
                .syncStatus(SyncStatus.PENDING_SYNC)
                .build();

        return taskRepositoryPort.save(task);
    }

    @Override
    public List<Task> execute() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public Optional<Task> execute(UUID id) {
        return taskRepositoryPort.findById(id);
    }
}
