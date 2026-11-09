package com.jira_clone.task_management_system.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequestDTO {

    @NotNull
    private Long taskId;

    @NotNull
    private Long userId;

    private String commentText;

}
