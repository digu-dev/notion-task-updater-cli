package com.digu.notiontaskupdatercli.adapter.out.memory;

import com.digu.notiontaskupdatercli.application.port.out.TaskRepositoryPort;
import com.digu.notiontaskupdatercli.domain.model.Task;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemoryTaskRepositoryAdapter implements TaskRepositoryPort {

    private final Map<UUID, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return tasks.values().stream()
                .sorted(Comparator.comparing(Task::getCreatedAt).thenComparing(Task::getTitle))
                .toList();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(tasks.get(id));
    }
}
