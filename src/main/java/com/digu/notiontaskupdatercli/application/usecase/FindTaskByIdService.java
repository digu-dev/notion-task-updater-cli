package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.dto.TaskDetails;
import com.digu.notiontaskupdatercli.application.port.in.FindTaskByIdUseCase;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class FindTaskByIdService implements FindTaskByIdUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public FindTaskByIdService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = Objects.requireNonNull(taskRepositoryPort, "Task repository is required.");
    }

    @Override
    public Optional<TaskDetails> findTaskById(UUID id) {
        Objects.requireNonNull(id, "Task id is required.");
        return taskRepositoryPort.findById(id).map(TaskDetails::from);
    }
}
