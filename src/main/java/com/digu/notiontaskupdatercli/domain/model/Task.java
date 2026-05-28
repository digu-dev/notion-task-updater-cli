package com.digu.notiontaskupdatercli.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Task {

    private final UUID id;
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
    private final String keyResultOwner;
    private final String bugOwner;
    private final TaskStatus status;
    private final String description;
    private final Integer sequenceToDo;
    private final Integer progressPercent;
    private final BigDecimal estimatedHours;
    private final LocalDate startDate;
    private final LocalDate dueDate;
    private final String dueDateDayOfWeek;
    private final String deliveryDeadline;
    private final BigDecimal hoursInvested;
    private final BigDecimal logHours;
    private final LocalDateTime completionDate;
    private final Boolean completedOnTime;
    private final String businessAccount;
    private final String contact;
    private final String howItWasDone;
    private final String tester;
    private final String relatedTasks;
    private final String endpointRelated;
    private final String address;
    private final String approvedBy;
    private final LocalDate approvedAt;
    private final String notionPageId;
    private final SyncStatus syncStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Task(Builder builder) {
        this.id = builder.id;
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
        this.keyResultOwner = builder.keyResultOwner;
        this.bugOwner = builder.bugOwner;
        this.status = builder.status;
        this.description = builder.description;
        this.sequenceToDo = builder.sequenceToDo;
        this.progressPercent = builder.progressPercent;
        this.estimatedHours = builder.estimatedHours;
        this.startDate = builder.startDate;
        this.dueDate = builder.dueDate;
        this.dueDateDayOfWeek = builder.dueDateDayOfWeek;
        this.deliveryDeadline = builder.deliveryDeadline;
        this.hoursInvested = builder.hoursInvested;
        this.logHours = builder.logHours;
        this.completionDate = builder.completionDate;
        this.completedOnTime = builder.completedOnTime;
        this.businessAccount = builder.businessAccount;
        this.contact = builder.contact;
        this.howItWasDone = builder.howItWasDone;
        this.tester = builder.tester;
        this.relatedTasks = builder.relatedTasks;
        this.endpointRelated = builder.endpointRelated;
        this.address = builder.address;
        this.approvedBy = builder.approvedBy;
        this.approvedAt = builder.approvedAt;
        this.notionPageId = builder.notionPageId;
        this.syncStatus = builder.syncStatus;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Task withSyncStatus(SyncStatus syncStatus) {
        return Task.builder()
                .id(this.id)
                .title(this.title)
                .type(this.type)
                .category(this.category)
                .subcategory(this.subcategory)
                .tags(this.tags)
                .horizon(this.horizon)
                .priority(this.priority)
                .importance(this.importance)
                .objective(this.objective)
                .severity(this.severity)
                .ownerToDo(this.ownerToDo)
                .keyResultOwner(this.keyResultOwner)
                .bugOwner(this.bugOwner)
                .status(this.status)
                .description(this.description)
                .sequenceToDo(this.sequenceToDo)
                .progressPercent(this.progressPercent)
                .estimatedHours(this.estimatedHours)
                .startDate(this.startDate)
                .dueDate(this.dueDate)
                .dueDateDayOfWeek(this.dueDateDayOfWeek)
                .deliveryDeadline(this.deliveryDeadline)
                .hoursInvested(this.hoursInvested)
                .logHours(this.logHours)
                .completionDate(this.completionDate)
                .completedOnTime(this.completedOnTime)
                .businessAccount(this.businessAccount)
                .contact(this.contact)
                .howItWasDone(this.howItWasDone)
                .tester(this.tester)
                .relatedTasks(this.relatedTasks)
                .endpointRelated(this.endpointRelated)
                .address(this.address)
                .approvedBy(this.approvedBy)
                .approvedAt(this.approvedAt)
                .notionPageId(this.notionPageId)
                .syncStatus(syncStatus)
                .createdAt(this.createdAt)
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UUID getId() { return id; }
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
    public String getKeyResultOwner() { return keyResultOwner; }
    public String getBugOwner() { return bugOwner; }
    public TaskStatus getStatus() { return status; }
    public String getDescription() { return description; }
    public Integer getSequenceToDo() { return sequenceToDo; }
    public Integer getProgressPercent() { return progressPercent; }
    public BigDecimal getEstimatedHours() { return estimatedHours; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getDueDateDayOfWeek() { return dueDateDayOfWeek; }
    public String getDeliveryDeadline() { return deliveryDeadline; }
    public BigDecimal getHoursInvested() { return hoursInvested; }
    public BigDecimal getLogHours() { return logHours; }
    public LocalDateTime getCompletionDate() { return completionDate; }
    public Boolean getCompletedOnTime() { return completedOnTime; }
    public String getBusinessAccount() { return businessAccount; }
    public String getContact() { return contact; }
    public String getHowItWasDone() { return howItWasDone; }
    public String getTester() { return tester; }
    public String getRelatedTasks() { return relatedTasks; }
    public String getEndpointRelated() { return endpointRelated; }
    public String getAddress() { return address; }
    public String getApprovedBy() { return approvedBy; }
    public LocalDate getApprovedAt() { return approvedAt; }
    public String getNotionPageId() { return notionPageId; }
    public SyncStatus getSyncStatus() { return syncStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public static final class Builder {
        private UUID id;
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
        private String keyResultOwner;
        private String bugOwner;
        private TaskStatus status;
        private String description;
        private Integer sequenceToDo;
        private Integer progressPercent;
        private BigDecimal estimatedHours;
        private LocalDate startDate;
        private LocalDate dueDate;
        private String dueDateDayOfWeek;
        private String deliveryDeadline;
        private BigDecimal hoursInvested;
        private BigDecimal logHours;
        private LocalDateTime completionDate;
        private Boolean completedOnTime;
        private String businessAccount;
        private String contact;
        private String howItWasDone;
        private String tester;
        private String relatedTasks;
        private String endpointRelated;
        private String address;
        private String approvedBy;
        private LocalDate approvedAt;
        private String notionPageId;
        private SyncStatus syncStatus = SyncStatus.PENDING_SYNC;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder id(UUID id) { this.id = id; return this; }
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
        public Builder keyResultOwner(String keyResultOwner) { this.keyResultOwner = keyResultOwner; return this; }
        public Builder bugOwner(String bugOwner) { this.bugOwner = bugOwner; return this; }
        public Builder status(TaskStatus status) { this.status = status; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder sequenceToDo(Integer sequenceToDo) { this.sequenceToDo = sequenceToDo; return this; }
        public Builder progressPercent(Integer progressPercent) { this.progressPercent = progressPercent; return this; }
        public Builder estimatedHours(BigDecimal estimatedHours) { this.estimatedHours = estimatedHours; return this; }
        public Builder startDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
        public Builder dueDateDayOfWeek(String dueDateDayOfWeek) { this.dueDateDayOfWeek = dueDateDayOfWeek; return this; }
        public Builder deliveryDeadline(String deliveryDeadline) { this.deliveryDeadline = deliveryDeadline; return this; }
        public Builder hoursInvested(BigDecimal hoursInvested) { this.hoursInvested = hoursInvested; return this; }
        public Builder logHours(BigDecimal logHours) { this.logHours = logHours; return this; }
        public Builder completionDate(LocalDateTime completionDate) { this.completionDate = completionDate; return this; }
        public Builder completedOnTime(Boolean completedOnTime) { this.completedOnTime = completedOnTime; return this; }
        public Builder businessAccount(String businessAccount) { this.businessAccount = businessAccount; return this; }
        public Builder contact(String contact) { this.contact = contact; return this; }
        public Builder howItWasDone(String howItWasDone) { this.howItWasDone = howItWasDone; return this; }
        public Builder tester(String tester) { this.tester = tester; return this; }
        public Builder relatedTasks(String relatedTasks) { this.relatedTasks = relatedTasks; return this; }
        public Builder endpointRelated(String endpointRelated) { this.endpointRelated = endpointRelated; return this; }
        public Builder address(String address) { this.address = address; return this; }
        public Builder approvedBy(String approvedBy) { this.approvedBy = approvedBy; return this; }
        public Builder approvedAt(LocalDate approvedAt) { this.approvedAt = approvedAt; return this; }
        public Builder notionPageId(String notionPageId) { this.notionPageId = notionPageId; return this; }
        public Builder syncStatus(SyncStatus syncStatus) { this.syncStatus = syncStatus; return this; }
        public Builder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Task build() {
            if (title == null || title.isBlank()) {
                throw new IllegalStateException("Task title must not be blank");
            }
            if (status == null) {
                status = TaskStatus.NOT_STARTED;
            }
            if (createdAt == null) {
                createdAt = LocalDateTime.now();
            }
            if (updatedAt == null) {
                updatedAt = createdAt;
            }
            return new Task(this);
        }
    }
}
