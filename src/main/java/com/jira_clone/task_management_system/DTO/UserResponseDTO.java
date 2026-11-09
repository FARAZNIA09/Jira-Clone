package com.jira_clone.task_management_system.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String mobileNumber;

    private Boolean isActive;

    private String roleName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
