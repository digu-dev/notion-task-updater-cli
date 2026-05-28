package com.digu.notiontaskupdatercli.application.port.in;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.domain.model.Task;

public interface CreateTaskUseCase {

    Task execute(CreateTaskCommand command);
}
