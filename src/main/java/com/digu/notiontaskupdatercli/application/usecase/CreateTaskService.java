package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.port.in.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import com.digu.notiontaskupdatercli.domain.model.Task;

import java.util.Objects;

public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public CreateTaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = Objects.requireNonNull(taskRepositoryPort, "taskRepositoryPort must not be null");
    }

    @Override
    public Task execute(CreateTaskCommand command) {
        Objects.requireNonNull(command, "command must not be null");

        Task task = Task.create(
                command.title(),
                command.description(),
                command.priority(),
                command.dueDate()
        );

        return taskRepositoryPort.save(task);
    }
}
