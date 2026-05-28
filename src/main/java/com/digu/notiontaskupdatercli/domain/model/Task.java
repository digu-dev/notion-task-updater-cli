package com.digu.notiontaskupdatercli.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private final UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private SyncStatus syncStatus;
    private LocalDateTime dueDate;
    private String notionPageId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
            LocalDateTime updatedAt
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.title = validateTitle(title);
        this.description = description;
        this.status = Objects.requireNonNull(status, "status must not be null");
        this.priority = Objects.requireNonNull(priority, "priority must not be null");
        this.syncStatus = Objects.requireNonNull(syncStatus, "syncStatus must not be null");
        this.dueDate = dueDate;
        this.notionPageId = notionPageId;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Task create(String title, String description, Priority priority, LocalDateTime dueDate) {
        LocalDateTime now = LocalDateTime.now();
        return new Task(
                UUID.randomUUID(),
                title,
                description,
                TaskStatus.TODO,
                priority == null ? Priority.MEDIUM : priority,
                SyncStatus.PENDING_SYNC,
                dueDate,
                null,
                now,
                now
        );
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

    public void updateTitle(String title) {
        this.title = validateTitle(title);
        touch();
    }

    public void updateDescription(String description) {
        this.description = description;
        touch();
    }

    public void updateStatus(TaskStatus status) {
        this.status = Objects.requireNonNull(status, "status must not be null");
        touch();
    }

    public void updatePriority(Priority priority) {
        this.priority = Objects.requireNonNull(priority, "priority must not be null");
        touch();
    }

    public void updateSyncStatus(SyncStatus syncStatus) {
        this.syncStatus = Objects.requireNonNull(syncStatus, "syncStatus must not be null");
        touch();
    }

    public void updateDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
        touch();
    }

    public void assignNotionPageId(String notionPageId) {
        this.notionPageId = notionPageId;
        touch();
    }

    private void touch() {
        this.updatedAt = LocalDateTime.now();
    }

    private String validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
        return title;
    }
}
