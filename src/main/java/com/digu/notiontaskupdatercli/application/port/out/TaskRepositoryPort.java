package com.digu.notiontaskupdatercli.application.port.out;

import com.digu.notiontaskupdatercli.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(UUID taskId);

    List<Task> findAll();
}
