package com.jira_clone.task_management_system.Mapper;


import com.jira_clone.task_management_system.DTO.UserRequestDTO;
import com.jira_clone.task_management_system.DTO.UserResponseDTO;
import com.jira_clone.task_management_system.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequestDTO dto) {

        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setMobileNumber(dto.getMobileNumber());
        return entity;
    }

    public UserResponseDTO toDTO(UserEntity entity) {

        return UserResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .isActive(entity.getIsActive())
                .roleName(
                        entity.getRole() != null
                                ? entity.getRole().getName()
                                : null
                )
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
