package com.task.management.task.model;

import com.task.management.profile.model.UserProfile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long task_id;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "created_date",nullable = false)
    private Date createdDate;
    @Column(name = "assigned_date")
    private Date assignedDate;
    @Column(name = "finished_date")
    private Date finishedDate;
    @Column(name = "programming_languages")
    private String programmingLanguages;
    @Column(name = "status")
    private String status;
    @Column(name = "feedback")
    private String feedback;

    public long getTask_id() {
        return task_id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(String programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + task_id +
                ", taskName='" + taskName + '\'' +
                ", assignedDate=" + assignedDate +
                ", finishedDate=" + finishedDate +
                ", programmingLanguages='" + programmingLanguages + '\'' +
                ", status='" + status + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
