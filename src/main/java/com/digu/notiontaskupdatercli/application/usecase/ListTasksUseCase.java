package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.domain.model.Task;

import java.util.List;

public interface ListTasksUseCase {

    List<Task> execute();
}
