package com.jira_clone.task_management_system.DTO;


import com.jira_clone.task_management_system.Enum.SprintStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SprintResponseDTO {

    private Long id;

    private String sprintName;

    private String goal;

    private LocalDate startDate;

    private LocalDate endDate;

    private SprintStatus status;

    private Long projectId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
