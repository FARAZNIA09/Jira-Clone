package com.jira_clone.task_management_system.DTO;

import com.jira_clone.task_management_system.Enum.TaskPriority;
import com.jira_clone.task_management_system.Enum.TaskStatus;
import jakarta.persistence.Entity;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequestDTO {

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    private Long projectId;

    private Long sprintId;

    private Long assignedTo;

    private Long createdBy;

    private LocalDate dueDate;

}
