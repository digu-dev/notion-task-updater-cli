package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.dto.CreateTaskResult;
import com.digu.notiontaskupdatercli.application.port.in.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import com.digu.notiontaskupdatercli.domain.model.Task;
import java.time.Clock;
import java.util.Objects;

public class CreateTaskService implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final Clock clock;

    public CreateTaskService(TaskRepositoryPort taskRepositoryPort, Clock clock) {
        this.taskRepositoryPort = Objects.requireNonNull(taskRepositoryPort, "Task repository is required.");
        this.clock = Objects.requireNonNull(clock, "Clock is required.");
    }

    @Override
    public CreateTaskResult createTask(CreateTaskCommand command) {
        Objects.requireNonNull(command, "Create task command is required.");

        Task task = Task.create(
                command.title(),
                command.description(),
                command.status(),
                command.priority(),
                command.dueDate(),
                clock);

        return CreateTaskResult.from(taskRepositoryPort.save(task));
    }
}
