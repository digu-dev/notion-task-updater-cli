package com.digu.notiontaskupdatercli.domain.model;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Task {

    private final UUID id;
    private final String title;
    private final String description;
    private final TaskStatus status;
    private final Priority priority;
    private final SyncStatus syncStatus;
    private final LocalDateTime dueDate;
    private final String notionPageId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Task(
            UUID id,
            String title,
            String description,
            TaskStatus status,
            Priority priority,
            SyncStatus syncStatus,
            LocalDateTime dueDate,
            String notionPageId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "Task id is required.");
        this.title = validateTitle(title);
        this.description = normalizeOptional(description);
        this.status = Objects.requireNonNull(status, "Task status is required.");
        this.priority = Objects.requireNonNull(priority, "Task priority is required.");
        this.syncStatus = Objects.requireNonNull(syncStatus, "Task sync status is required.");
        this.dueDate = dueDate;
        this.notionPageId = normalizeOptional(notionPageId);
        this.createdAt = Objects.requireNonNull(createdAt, "Task creation date is required.");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Task update date is required.");
    }

    public static Task create(
            String title,
            String description,
            TaskStatus status,
            Priority priority,
            LocalDateTime dueDate,
            Clock clock) {
        Objects.requireNonNull(clock, "Clock is required.");

        LocalDateTime now = LocalDateTime.now(clock);
        return new Task(
                UUID.randomUUID(),
                title,
                description,
                status,
                priority,
                SyncStatus.PENDING_SYNC,
                dueDate,
                null,
                now,
                now);
    }

    private static String validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }

        return title.trim();
    }

    private static String normalizeOptional(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public SyncStatus getSyncStatus() {
        return syncStatus;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getNotionPageId() {
        return notionPageId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
