package com.digu.notiontaskupdatercli.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void shouldCreateTaskWithValidData() {
        LocalDateTime dueDate = LocalDateTime.now().plusDays(1);

        Task task = Task.create("Minha task", "Descrição", Priority.HIGH, dueDate);

        assertNotNull(task.getId());
        assertEquals("Minha task", task.getTitle());
        assertEquals("Descrição", task.getDescription());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(SyncStatus.PENDING_SYNC, task.getSyncStatus());
        assertEquals(dueDate, task.getDueDate());
        assertNull(task.getNotionPageId());
        assertNotNull(task.getCreatedAt());
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    void shouldFailWhenCreatingTaskWithoutTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> Task.create("   ", "Descrição", Priority.MEDIUM, null));
    }
}
