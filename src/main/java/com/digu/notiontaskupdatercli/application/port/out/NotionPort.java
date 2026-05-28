package com.digu.notiontaskupdatercli.application.port.out;

import com.digu.notiontaskupdatercli.domain.model.Task;

public interface NotionPort {

    String upsertTaskPage(Task task);
}
