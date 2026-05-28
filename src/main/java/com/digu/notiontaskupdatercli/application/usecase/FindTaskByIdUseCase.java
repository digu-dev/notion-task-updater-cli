package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.domain.model.Task;

import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdUseCase {

    Optional<Task> execute(UUID id);
}
