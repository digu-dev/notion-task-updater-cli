package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.command.CreateTaskCommand;
import com.digu.notiontaskupdatercli.domain.model.Task;

public interface CreateTaskUseCase {

    Task execute(CreateTaskCommand command);
}
