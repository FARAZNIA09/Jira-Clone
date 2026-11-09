package com.jira_clone.task_management_system.Mapper;

import com.jira_clone.task_management_system.DTO.RoleRequestDTO;
import com.jira_clone.task_management_system.DTO.RoleResponseDTO;
import com.jira_clone.task_management_system.Entity.RoleEntity;
import com.jira_clone.task_management_system.Repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleEntity toEntity(RoleRequestDTO dto) {

        RoleEntity entity = new RoleEntity();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }

    public RoleResponseDTO toResponseDTO(RoleEntity entity) {

        return RoleResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
