package com.jira_clone.task_management_system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentRequestDTO {

    @NotNull
    private Long taskId;

    @NotBlank
    private String fileName;

    private String filePath;

    @NotNull
    private Long uploadedBy;
}