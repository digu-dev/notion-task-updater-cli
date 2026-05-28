package com.digu.notiontaskupdatercli.application.port.out;

import com.digu.notiontaskupdatercli.domain.model.Task;

public interface TaskEventPublisherPort {

    void publishTaskCreated(Task task);
}
