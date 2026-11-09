package com.jira_clone.task_management_system.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequestDTO {

//    private String projectKey;
    @NotNull
    private String projectName;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long createdBy;
}
