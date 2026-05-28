package com.digu.notiontaskupdatercli.adapter.in.cli;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.dto.CreateTaskResult;
import com.digu.notiontaskupdatercli.application.dto.TaskDetails;
import com.digu.notiontaskupdatercli.application.dto.TaskSummary;
import com.digu.notiontaskupdatercli.application.port.in.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.port.in.FindTaskByIdUseCase;
import com.digu.notiontaskupdatercli.application.port.in.ListTasksUseCase;
import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskCommandsTest {

    @Mock
    private CreateTaskUseCase createTaskUseCase;

    @Mock
    private ListTasksUseCase listTasksUseCase;

    @Mock
    private FindTaskByIdUseCase findTaskByIdUseCase;

    @Mock
    private CliPrompt cliPrompt;

    private TaskCommands taskCommands;

    @BeforeEach
    void setUp() {
        taskCommands = new TaskCommands(
                createTaskUseCase,
                listTasksUseCase,
                findTaskByIdUseCase,
                cliPrompt,
                new TaskCliInputParser(),
                new TaskCliFormatter());
    }

    @Test
    void shouldCreateTaskWithValidInteractiveInput() {
        UUID taskId = UUID.randomUUID();
        when(cliPrompt.ask("Title")).thenReturn("My task");
        when(cliPrompt.askOptional("Description")).thenReturn("Create via CLI");
        when(cliPrompt.ask("Status (TODO, IN_PROGRESS, DONE)")).thenReturn("in progress");
        when(cliPrompt.ask("Priority (LOW, MEDIUM, HIGH)")).thenReturn("high");
        when(cliPrompt.askOptional("Due date (optional, yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss)"))
                .thenReturn("2026-06-01");
        when(createTaskUseCase.createTask(any(CreateTaskCommand.class)))
                .thenReturn(new CreateTaskResult(taskId, "My task", TaskStatus.IN_PROGRESS, SyncStatus.PENDING_SYNC));

        String output = taskCommands.create();

        ArgumentCaptor<CreateTaskCommand> commandCaptor = ArgumentCaptor.forClass(CreateTaskCommand.class);
        verify(createTaskUseCase).createTask(commandCaptor.capture());
        assertThat(commandCaptor.getValue().title()).isEqualTo("My task");
        assertThat(commandCaptor.getValue().description()).isEqualTo("Create via CLI");
        assertThat(commandCaptor.getValue().status()).isEqualTo(TaskStatus.IN_PROGRESS);
        assertThat(commandCaptor.getValue().priority()).isEqualTo(Priority.HIGH);
        assertThat(commandCaptor.getValue().dueDate()).isEqualTo(LocalDateTime.of(2026, 6, 1, 0, 0));
        assertThat(output)
                .contains("SUCCESS: Task created.")
                .contains(taskId.toString())
                .contains("Status: IN_PROGRESS")
                .contains("Sync status: PENDING_SYNC");
    }

    @Test
    void shouldReturnValidationErrorWhenTitleIsBlank() {
        when(cliPrompt.ask("Title")).thenReturn("   ");

        String output = taskCommands.create();

        verify(createTaskUseCase, never()).createTask(any(CreateTaskCommand.class));
        assertThat(output).isEqualTo("ERROR: Title is required.");
    }

    @Test
    void shouldReturnValidationErrorWhenStatusIsInvalid() {
        when(cliPrompt.ask("Title")).thenReturn("My task");
        when(cliPrompt.askOptional("Description")).thenReturn("Description");
        when(cliPrompt.ask("Status (TODO, IN_PROGRESS, DONE)")).thenReturn("blocked");

        String output = taskCommands.create();

        verify(createTaskUseCase, never()).createTask(any(CreateTaskCommand.class));
        assertThat(output)
                .isEqualTo("ERROR: Invalid status 'blocked'. Allowed values: TODO, IN_PROGRESS, DONE.");
    }

    @Test
    void shouldListTasksWithFormattedTable() {
        UUID taskId = UUID.randomUUID();
        when(listTasksUseCase.listTasks())
                .thenReturn(List.of(new TaskSummary(
                        taskId,
                        "Task title",
                        TaskStatus.TODO,
                        Priority.MEDIUM,
                        SyncStatus.PENDING_SYNC,
                        LocalDateTime.of(2026, 6, 10, 9, 0))));

        String output = taskCommands.list();

        assertThat(output)
                .contains("ID")
                .contains(taskId.toString())
                .contains("Task title")
                .contains("PENDING_SYNC")
                .contains("2026-06-10T09:00:00");
    }

    @Test
    void shouldReturnFriendlyMessageWhenListIsEmpty() {
        when(listTasksUseCase.listTasks()).thenReturn(List.of());

        String output = taskCommands.list();

        assertThat(output).isEqualTo("No tasks found.");
    }

    @Test
    void shouldShowTaskDetailsForExistingTask() {
        UUID taskId = UUID.randomUUID();
        when(findTaskByIdUseCase.findTaskById(taskId))
                .thenReturn(Optional.of(new TaskDetails(
                        taskId,
                        "Existing task",
                        "Detailed description",
                        TaskStatus.DONE,
                        Priority.HIGH,
                        SyncStatus.SYNCED,
                        LocalDateTime.of(2026, 7, 1, 10, 30),
                        "page-123",
                        LocalDateTime.of(2026, 5, 1, 8, 0),
                        LocalDateTime.of(2026, 5, 2, 8, 0))));

        String output = taskCommands.show(taskId.toString());

        assertThat(output)
                .contains("Id: " + taskId)
                .contains("Title: Existing task")
                .contains("Description: Detailed description")
                .contains("Notion page id: page-123")
                .contains("Created at: 2026-05-01T08:00:00");
    }

    @Test
    void shouldReturnFriendlyMessageWhenTaskIsNotFound() {
        UUID taskId = UUID.randomUUID();
        when(findTaskByIdUseCase.findTaskById(taskId)).thenReturn(Optional.empty());

        String output = taskCommands.show(taskId.toString());

        assertThat(output).isEqualTo("ERROR: Task not found for id " + taskId + ".");
    }

    @Test
    void shouldValidateUuidBeforeSearchingTask() {
        String output = taskCommands.show("invalid-uuid");

        verify(findTaskByIdUseCase, never()).findTaskById(any(UUID.class));
        assertThat(output).isEqualTo("ERROR: Invalid task id. Use a valid UUID.");
    }
}
