package com.digu.notiontaskupdatercli.adapter.out.persistence;

import com.digu.notiontaskupdatercli.domain.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TaskEntityMapper {

    public TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setType(task.getType());
        entity.setCategory(task.getCategory());
        entity.setSubcategory(task.getSubcategory());
        entity.setTags(task.getTags() != null ? new ArrayList<>(task.getTags()) : new ArrayList<>());
        entity.setHorizon(task.getHorizon());
        entity.setPriority(task.getPriority());
        entity.setImportance(task.getImportance());
        entity.setObjective(task.getObjective());
        entity.setSeverity(task.getSeverity());
        entity.setOwnerToDo(task.getOwnerToDo());
        entity.setKeyResultOwner(task.getKeyResultOwner());
        entity.setBugOwner(task.getBugOwner());
        entity.setStatus(task.getStatus());
        entity.setDescription(task.getDescription());
        entity.setSequenceToDo(task.getSequenceToDo());
        entity.setProgressPercent(task.getProgressPercent());
        entity.setEstimatedHours(task.getEstimatedHours());
        entity.setStartDate(task.getStartDate());
        entity.setDueDate(task.getDueDate());
        entity.setDueDateDayOfWeek(task.getDueDateDayOfWeek());
        entity.setDeliveryDeadline(task.getDeliveryDeadline());
        entity.setHoursInvested(task.getHoursInvested());
        entity.setLogHours(task.getLogHours());
        entity.setCompletionDate(task.getCompletionDate());
        entity.setCompletedOnTime(task.getCompletedOnTime());
        entity.setBusinessAccount(task.getBusinessAccount());
        entity.setContact(task.getContact());
        entity.setHowItWasDone(task.getHowItWasDone());
        entity.setTester(task.getTester());
        entity.setRelatedTasks(task.getRelatedTasks());
        entity.setEndpointRelated(task.getEndpointRelated());
        entity.setAddress(task.getAddress());
        entity.setApprovedBy(task.getApprovedBy());
        entity.setApprovedAt(task.getApprovedAt());
        entity.setNotionPageId(task.getNotionPageId());
        entity.setSyncStatus(task.getSyncStatus());
        entity.setCreatedAt(task.getCreatedAt());
        entity.setUpdatedAt(task.getUpdatedAt());
        return entity;
    }

    public Task toDomain(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .type(entity.getType())
                .category(entity.getCategory())
                .subcategory(entity.getSubcategory())
                .tags(entity.getTags() != null ? new ArrayList<>(entity.getTags()) : new ArrayList<>())
                .horizon(entity.getHorizon())
                .priority(entity.getPriority())
                .importance(entity.getImportance())
                .objective(entity.getObjective())
                .severity(entity.getSeverity())
                .ownerToDo(entity.getOwnerToDo())
                .keyResultOwner(entity.getKeyResultOwner())
                .bugOwner(entity.getBugOwner())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .sequenceToDo(entity.getSequenceToDo())
                .progressPercent(entity.getProgressPercent())
                .estimatedHours(entity.getEstimatedHours())
                .startDate(entity.getStartDate())
                .dueDate(entity.getDueDate())
                .dueDateDayOfWeek(entity.getDueDateDayOfWeek())
                .deliveryDeadline(entity.getDeliveryDeadline())
                .hoursInvested(entity.getHoursInvested())
                .logHours(entity.getLogHours())
                .completionDate(entity.getCompletionDate())
                .completedOnTime(entity.getCompletedOnTime())
                .businessAccount(entity.getBusinessAccount())
                .contact(entity.getContact())
                .howItWasDone(entity.getHowItWasDone())
                .tester(entity.getTester())
                .relatedTasks(entity.getRelatedTasks())
                .endpointRelated(entity.getEndpointRelated())
                .address(entity.getAddress())
                .approvedBy(entity.getApprovedBy())
                .approvedAt(entity.getApprovedAt())
                .notionPageId(entity.getNotionPageId())
                .syncStatus(entity.getSyncStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
