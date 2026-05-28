package com.digu.notiontaskupdatercli.application.usecase;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTaskServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @InjectMocks
    private CreateTaskService createTaskService;

    @Test
    void shouldCreateTaskAndPersistUsingRepositoryPort() {
        CreateTaskCommand command = new CreateTaskCommand(
                "Criar arquitetura hexagonal",
                "Implementar domínio",
                Priority.MEDIUM,
                LocalDateTime.now().plusDays(2)
        );

        when(taskRepositoryPort.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task createdTask = createTaskService.execute(command);

        assertNotNull(createdTask.getId());
        assertEquals(command.title(), createdTask.getTitle());
        verify(taskRepositoryPort).save(any(Task.class));
    }

    @Test
    void shouldSetPendingSyncStatusOnCreationFlow() {
        CreateTaskCommand command = new CreateTaskCommand("Nova task", null, Priority.LOW, null);

        when(taskRepositoryPort.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        createTaskService.execute(command);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepositoryPort).save(taskCaptor.capture());
        assertEquals(SyncStatus.PENDING_SYNC, taskCaptor.getValue().getSyncStatus());
    }
}
