package com.digu.notiontaskupdatercli.adapter.out.persistence;

import com.digu.notiontaskupdatercli.domain.model.Priority;
import com.digu.notiontaskupdatercli.domain.model.SyncStatus;
import com.digu.notiontaskupdatercli.domain.model.TaskStatus;
import com.digu.notiontaskupdatercli.domain.model.TaskType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50)
    private TaskType type;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "subcategory", length = 100)
    private String subcategory;

    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "tag", length = 100)
    private List<String> tags = new ArrayList<>();

    @Column(name = "horizon")
    private Integer horizon;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 50)
    private Priority priority;

    @Column(name = "importance", length = 100)
    private String importance;

    @Column(name = "objective", length = 100)
    private String objective;

    @Column(name = "severity", length = 50)
    private String severity;

    @Column(name = "owner_to_do", length = 255)
    private String ownerToDo;

    @Column(name = "key_result_owner", length = 255)
    private String keyResultOwner;

    @Column(name = "bug_owner", length = 255)
    private String bugOwner;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private TaskStatus status;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "sequence_to_do")
    private Integer sequenceToDo;

    @Column(name = "progress_percent")
    private Integer progressPercent;

    @Column(name = "estimated_hours", precision = 10, scale = 2)
    private BigDecimal estimatedHours;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "due_date_day_of_week", length = 50)
    private String dueDateDayOfWeek;

    @Column(name = "delivery_deadline", length = 100)
    private String deliveryDeadline;

    @Column(name = "hours_invested", precision = 10, scale = 2)
    private BigDecimal hoursInvested;

    @Column(name = "log_hours", precision = 10, scale = 2)
    private BigDecimal logHours;

    @Column(name = "completion_date")
    private LocalDateTime completionDate;

    @Column(name = "completed_on_time")
    private Boolean completedOnTime;

    @Column(name = "business_account", length = 255)
    private String businessAccount;

    @Column(name = "contact", length = 255)
    private String contact;

    @Column(name = "how_it_was_done", columnDefinition = "TEXT")
    private String howItWasDone;

    @Column(name = "tester", length = 255)
    private String tester;

    @Column(name = "related_tasks", columnDefinition = "TEXT")
    private String relatedTasks;

    @Column(name = "endpoint_related", length = 500)
    private String endpointRelated;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "approved_by", length = 255)
    private String approvedBy;

    @Column(name = "approved_at")
    private LocalDate approvedAt;

    @Column(name = "notion_page_id", length = 255)
    private String notionPageId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sync_status", nullable = false, length = 50)
    private SyncStatus syncStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public TaskEntity() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSubcategory() { return subcategory; }
    public void setSubcategory(String subcategory) { this.subcategory = subcategory; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public Integer getHorizon() { return horizon; }
    public void setHorizon(Integer horizon) { this.horizon = horizon; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public String getImportance() { return importance; }
    public void setImportance(String importance) { this.importance = importance; }
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public String getOwnerToDo() { return ownerToDo; }
    public void setOwnerToDo(String ownerToDo) { this.ownerToDo = ownerToDo; }
    public String getKeyResultOwner() { return keyResultOwner; }
    public void setKeyResultOwner(String keyResultOwner) { this.keyResultOwner = keyResultOwner; }
    public String getBugOwner() { return bugOwner; }
    public void setBugOwner(String bugOwner) { this.bugOwner = bugOwner; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getSequenceToDo() { return sequenceToDo; }
    public void setSequenceToDo(Integer sequenceToDo) { this.sequenceToDo = sequenceToDo; }
    public Integer getProgressPercent() { return progressPercent; }
    public void setProgressPercent(Integer progressPercent) { this.progressPercent = progressPercent; }
    public BigDecimal getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(BigDecimal estimatedHours) { this.estimatedHours = estimatedHours; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getDueDateDayOfWeek() { return dueDateDayOfWeek; }
    public void setDueDateDayOfWeek(String dueDateDayOfWeek) { this.dueDateDayOfWeek = dueDateDayOfWeek; }
    public String getDeliveryDeadline() { return deliveryDeadline; }
    public void setDeliveryDeadline(String deliveryDeadline) { this.deliveryDeadline = deliveryDeadline; }
    public BigDecimal getHoursInvested() { return hoursInvested; }
    public void setHoursInvested(BigDecimal hoursInvested) { this.hoursInvested = hoursInvested; }
    public BigDecimal getLogHours() { return logHours; }
    public void setLogHours(BigDecimal logHours) { this.logHours = logHours; }
    public LocalDateTime getCompletionDate() { return completionDate; }
    public void setCompletionDate(LocalDateTime completionDate) { this.completionDate = completionDate; }
    public Boolean getCompletedOnTime() { return completedOnTime; }
    public void setCompletedOnTime(Boolean completedOnTime) { this.completedOnTime = completedOnTime; }
    public String getBusinessAccount() { return businessAccount; }
    public void setBusinessAccount(String businessAccount) { this.businessAccount = businessAccount; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public String getHowItWasDone() { return howItWasDone; }
    public void setHowItWasDone(String howItWasDone) { this.howItWasDone = howItWasDone; }
    public String getTester() { return tester; }
    public void setTester(String tester) { this.tester = tester; }
    public String getRelatedTasks() { return relatedTasks; }
    public void setRelatedTasks(String relatedTasks) { this.relatedTasks = relatedTasks; }
    public String getEndpointRelated() { return endpointRelated; }
    public void setEndpointRelated(String endpointRelated) { this.endpointRelated = endpointRelated; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDate getApprovedAt() { return approvedAt; }
    public void setApprovedAt(LocalDate approvedAt) { this.approvedAt = approvedAt; }
    public String getNotionPageId() { return notionPageId; }
    public void setNotionPageId(String notionPageId) { this.notionPageId = notionPageId; }
    public SyncStatus getSyncStatus() { return syncStatus; }
    public void setSyncStatus(SyncStatus syncStatus) { this.syncStatus = syncStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
