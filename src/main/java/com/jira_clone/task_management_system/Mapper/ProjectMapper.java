package com.jira_clone.task_management_system.Mapper;

import com.jira_clone.task_management_system.DTO.ProjectRequestDTO;
import com.jira_clone.task_management_system.DTO.ProjectResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectEntity toEntity(ProjectRequestDTO dto){

        ProjectEntity entity = new ProjectEntity();
        entity.setProjectName(dto.getProjectName());
//        entity.setProjectKey(dto.getProjectKey());
        entity.setDescription(dto.getDescription());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setCreatedBy(dto.getCreatedBy());
        return entity;
    }

    public ProjectResponseDTO toDTO(ProjectEntity entity){
        ProjectResponseDTO dto = new ProjectResponseDTO();

        dto.setId(entity.getId());
        dto.setProjectName(entity.getProjectName());
        dto.setProjectKey(entity.getProjectKey());
        dto.setDescription(entity.getDescription());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

}
