package com.jira_clone.task_management_system.DTO;


import com.jira_clone.task_management_system.Enum.SprintStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SprintRequestDTO {

    @NotBlank
    private String sprintName;

    private String goal;

    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private SprintStatus status;

    @NotNull
    private Long projectId;
}
