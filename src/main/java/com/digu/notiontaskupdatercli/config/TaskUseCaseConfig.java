package com.digu.notiontaskupdatercli.config;

import com.digu.notiontaskupdatercli.application.port.in.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.port.in.FindTaskByIdUseCase;
import com.digu.notiontaskupdatercli.application.port.in.ListTasksUseCase;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import com.digu.notiontaskupdatercli.application.usecase.CreateTaskService;
import com.digu.notiontaskupdatercli.application.usecase.FindTaskByIdService;
import com.digu.notiontaskupdatercli.application.usecase.ListTasksService;
import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskUseCaseConfig {

    @Bean
    Clock systemClock() {
        return Clock.systemUTC();
    }

    @Bean
    CreateTaskUseCase createTaskUseCase(TaskRepositoryPort taskRepositoryPort, Clock systemClock) {
        return new CreateTaskService(taskRepositoryPort, systemClock);
    }

    @Bean
    ListTasksUseCase listTasksUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new ListTasksService(taskRepositoryPort);
    }

    @Bean
    FindTaskByIdUseCase findTaskByIdUseCase(TaskRepositoryPort taskRepositoryPort) {
        return new FindTaskByIdService(taskRepositoryPort);
    }
}
