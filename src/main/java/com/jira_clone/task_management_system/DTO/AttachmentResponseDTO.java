package com.jira_clone.task_management_system.DTO;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttachmentResponseDTO {

    private Long id;
    private Long taskId;
    private String fileName;
    private String filePath;
    private Long uploadedBy;
    private LocalDateTime uploadedAt;
}