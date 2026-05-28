package com.digu.notiontaskupdatercli.application.port.in;

import com.digu.notiontaskupdatercli.application.dto.TaskSummary;
import java.util.List;

public interface ListTasksUseCase {

    List<TaskSummary> listTasks();
}
