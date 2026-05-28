package com.digu.notiontaskupdatercli.application.port.in;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.dto.CreateTaskResult;

public interface CreateTaskUseCase {

    CreateTaskResult createTask(CreateTaskCommand command);
}
