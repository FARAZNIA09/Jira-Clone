package com.jira_clone.task_management_system.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestDTO {

    @Schema(
            name = "name",
            description = "Enter Your Role"
    )
    @NotBlank(message = "Role name is required")
    private String name;

    @Schema(
            description = "Work Description"
    )
    private String description;


}
