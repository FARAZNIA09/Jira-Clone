package com.jira_clone.task_management_system.DTO;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponseDTO {

    private Long id;

    private Long userId;

    private Long taskId;

    private String commentText;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
