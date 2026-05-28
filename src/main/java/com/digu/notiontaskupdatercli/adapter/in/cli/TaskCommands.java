package com.digu.notiontaskupdatercli.adapter.in.cli;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskCommand;
import com.digu.notiontaskupdatercli.application.port.in.CreateTaskUseCase;
import com.digu.notiontaskupdatercli.application.port.in.FindTaskByIdUseCase;
import com.digu.notiontaskupdatercli.application.port.in.ListTasksUseCase;
import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class TaskCommands {

    private final CreateTaskUseCase createTaskUseCase;
    private final ListTasksUseCase listTasksUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final CliPrompt cliPrompt;
    private final TaskCliInputParser taskCliInputParser;
    private final TaskCliFormatter taskCliFormatter;

    public TaskCommands(
            CreateTaskUseCase createTaskUseCase,
            ListTasksUseCase listTasksUseCase,
            FindTaskByIdUseCase findTaskByIdUseCase,
            CliPrompt cliPrompt,
            TaskCliInputParser taskCliInputParser,
            TaskCliFormatter taskCliFormatter) {
        this.createTaskUseCase = createTaskUseCase;
        this.listTasksUseCase = listTasksUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
        this.cliPrompt = cliPrompt;
        this.taskCliInputParser = taskCliInputParser;
        this.taskCliFormatter = taskCliFormatter;
    }

    @ShellMethod(key = "task:create", value = "Creates a task through an interactive flow.")
    public String create() {
        try {
            String title = taskCliInputParser.parseTitle(cliPrompt.ask("Title"));
            String description = cliPrompt.askOptional("Description");
            TaskStatus status = taskCliInputParser.parseStatus(cliPrompt.ask("Status (TODO, IN_PROGRESS, DONE)"));
            Priority priority = taskCliInputParser.parsePriority(cliPrompt.ask("Priority (LOW, MEDIUM, HIGH)"));
            LocalDateTime dueDate = taskCliInputParser.parseDueDate(
                    cliPrompt.askOptional("Due date (optional, yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss)"));

            return taskCliFormatter.formatSuccess(createTaskUseCase.createTask(
                    new CreateTaskCommand(title, description, status, priority, dueDate)));
        } catch (CliValidationException | IllegalArgumentException exception) {
            return taskCliFormatter.formatError(exception.getMessage());
        }
    }

    @ShellMethod(key = "task:list", value = "Lists all tasks.")
    public String list() {
        return taskCliFormatter.formatTaskList(listTasksUseCase.listTasks());
    }

    @ShellMethod(key = "task:show", value = "Shows task details by id.")
    public String show(@ShellOption(value = "--id", help = "Task UUID") String id) {
        try {
            UUID taskId = taskCliInputParser.parseUuid(id);
            return findTaskByIdUseCase.findTaskById(taskId)
                    .map(taskCliFormatter::formatTaskDetails)
                    .orElseGet(() -> taskCliFormatter.formatError("Task not found for id " + taskId + "."));
        } catch (CliValidationException exception) {
            return taskCliFormatter.formatError(exception.getMessage());
        }
    }
}
