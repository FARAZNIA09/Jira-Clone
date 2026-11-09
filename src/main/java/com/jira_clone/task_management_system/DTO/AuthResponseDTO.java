
package com.jira_clone.task_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    private String accessToken;

    private String tokenType;

    private Long userId;

    private String email;

    private String role;
}
