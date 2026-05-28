package com.digu.notiontaskupdatercli.application.command;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import com.digu.notiontaskupdatercli.domain.model.TaskType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CreateTaskCommand {

    private final String title;
    private final TaskType type;
    private final String category;
    private final String subcategory;
    private final List<String> tags;
    private final Integer horizon;
    private final Priority priority;
    private final String importance;
    private final String objective;
    private final String severity;
    private final String ownerToDo;
    private final TaskStatus status;
    private final String description;
    private final BigDecimal estimatedHours;
    private final LocalDate startDate;
    private final LocalDate dueDate;

    private CreateTaskCommand(Builder builder) {
        this.title = builder.title;
        this.type = builder.type;
        this.category = builder.category;
        this.subcategory = builder.subcategory;
        this.tags = builder.tags;
        this.horizon = builder.horizon;
        this.priority = builder.priority;
        this.importance = builder.importance;
        this.objective = builder.objective;
        this.severity = builder.severity;
        this.ownerToDo = builder.ownerToDo;
        this.status = builder.status;
        this.description = builder.description;
        this.estimatedHours = builder.estimatedHours;
        this.startDate = builder.startDate;
        this.dueDate = builder.dueDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() { return title; }
    public TaskType getType() { return type; }
    public String getCategory() { return category; }
    public String getSubcategory() { return subcategory; }
    public List<String> getTags() { return tags; }
    public Integer getHorizon() { return horizon; }
    public Priority getPriority() { return priority; }
    public String getImportance() { return importance; }
    public String getObjective() { return objective; }
    public String getSeverity() { return severity; }
    public String getOwnerToDo() { return ownerToDo; }
    public TaskStatus getStatus() { return status; }
    public String getDescription() { return description; }
    public BigDecimal getEstimatedHours() { return estimatedHours; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }

    public static final class Builder {
        private String title;
        private TaskType type;
        private String category;
        private String subcategory;
        private List<String> tags;
        private Integer horizon;
        private Priority priority;
        private String importance;
        private String objective;
        private String severity;
        private String ownerToDo;
        private TaskStatus status;
        private String description;
        private BigDecimal estimatedHours;
        private LocalDate startDate;
        private LocalDate dueDate;

        public Builder title(String title) { this.title = title; return this; }
        public Builder type(TaskType type) { this.type = type; return this; }
        public Builder category(String category) { this.category = category; return this; }
        public Builder subcategory(String subcategory) { this.subcategory = subcategory; return this; }
        public Builder tags(List<String> tags) { this.tags = tags; return this; }
        public Builder horizon(Integer horizon) { this.horizon = horizon; return this; }
        public Builder priority(Priority priority) { this.priority = priority; return this; }
        public Builder importance(String importance) { this.importance = importance; return this; }
        public Builder objective(String objective) { this.objective = objective; return this; }
        public Builder severity(String severity) { this.severity = severity; return this; }
        public Builder ownerToDo(String ownerToDo) { this.ownerToDo = ownerToDo; return this; }
        public Builder status(TaskStatus status) { this.status = status; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder estimatedHours(BigDecimal estimatedHours) { this.estimatedHours = estimatedHours; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }

        public CreateTaskCommand build() {
            return new CreateTaskCommand(this);
        }
    }
}
