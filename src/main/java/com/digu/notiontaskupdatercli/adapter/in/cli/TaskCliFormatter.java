package com.digu.notiontaskupdatercli.adapter.in.cli;

import com.digu.notiontaskupdatercli.application.dto.CreateTaskResult;
import com.digu.notiontaskupdatercli.application.dto.TaskDetails;
import com.digu.notiontaskupdatercli.application.dto.TaskSummary;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TaskCliFormatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final String LIST_HEADER = String.format(
            "%-36s | %-24s | %-12s | %-8s | %-13s | %-19s",
            "ID",
            "TITLE",
            "STATUS",
            "PRIORITY",
            "SYNC_STATUS",
            "DUE_DATE");

    public String formatSuccess(CreateTaskResult result) {
        return String.join(
                System.lineSeparator(),
                "SUCCESS: Task created.",
                "Id: " + result.id(),
                "Title: " + result.title(),
                "Status: " + result.status(),
                "Sync status: " + result.syncStatus());
    }

    public String formatTaskList(List<TaskSummary> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks found.";
        }

        String separator = "-".repeat(LIST_HEADER.length());
        String rows = tasks.stream()
                .map(task -> String.format(
                        "%-36s | %-24s | %-12s | %-8s | %-13s | %-19s",
                        task.id(),
                        truncate(task.title(), 24),
                        task.status(),
                        task.priority(),
                        task.syncStatus(),
                        formatDateTime(task.dueDate())))
                .reduce((left, right) -> left + System.lineSeparator() + right)
                .orElse("");

        return String.join(System.lineSeparator(), LIST_HEADER, separator, rows);
    }

    public String formatTaskDetails(TaskDetails task) {
        return String.join(
                System.lineSeparator(),
                "Id: " + task.id(),
                "Title: " + task.title(),
                "Description: " + formatValue(task.description()),
                "Status: " + task.status(),
                "Priority: " + task.priority(),
                "Sync status: " + task.syncStatus(),
                "Due date: " + formatDateTime(task.dueDate()),
                "Notion page id: " + formatValue(task.notionPageId()),
                "Created at: " + formatDateTime(task.createdAt()),
                "Updated at: " + formatDateTime(task.updatedAt()));
    }

    public String formatError(String message) {
        return "ERROR: " + message;
    }

    private String truncate(String value, int maxLength) {
        if (value == null || value.length() <= maxLength) {
            return formatValue(value);
        }

        return value.substring(0, maxLength - 3) + "...";
    }

    private String formatValue(String value) {
        return value == null ? "-" : value;
    }

    private String formatDateTime(LocalDateTime value) {
        return value == null ? "-" : DATE_TIME_FORMATTER.format(value);
    }
}
