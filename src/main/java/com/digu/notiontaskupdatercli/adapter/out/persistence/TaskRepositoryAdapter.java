package com.digu.notiontaskupdatercli.adapter.out.persistence;

import com.digu.notiontaskupdatercli.domain.model.Task;
import com.digu.notiontaskupdatercli.domain.port.TaskRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskEntityMapper taskEntityMapper;

    public TaskRepositoryAdapter(TaskJpaRepository taskJpaRepository, TaskEntityMapper taskEntityMapper) {
        this.taskJpaRepository = taskJpaRepository;
        this.taskEntityMapper = taskEntityMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskEntityMapper.toEntity(task);
        TaskEntity saved = taskJpaRepository.save(entity);
        return taskEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskJpaRepository.findById(id)
                .map(taskEntityMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll()
                .stream()
                .map(taskEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}
