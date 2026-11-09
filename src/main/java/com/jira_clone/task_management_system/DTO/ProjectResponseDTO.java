package com.jira_clone.task_management_system.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDTO {

    private Long id;

    private String projectName;

    private String projectKey;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
