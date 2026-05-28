package com.digu.notiontaskupdatercli.application.port.in;

import com.digu.notiontaskupdatercli.application.dto.TaskDetails;
import java.util.Optional;
import java.util.UUID;

public interface FindTaskByIdUseCase {

    Optional<TaskDetails> findTaskById(UUID id);
}
