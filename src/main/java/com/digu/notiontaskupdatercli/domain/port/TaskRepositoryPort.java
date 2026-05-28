package com.digu.notiontaskupdatercli.domain.port;

import com.digu.notiontaskupdatercli.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {

    Task save(Task task);

    Optional<Task> findById(UUID id);

    List<Task> findAll();
}
