package com.jira_clone.task_management_system.Mapper;


import com.jira_clone.task_management_system.DTO.SprintRequestDTO;
import com.jira_clone.task_management_system.DTO.SprintResponseDTO;
import com.jira_clone.task_management_system.Entity.SprintEntity;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {

    public SprintEntity toEntity(SprintRequestDTO dto){
        SprintEntity entity = new SprintEntity();

        entity.setSprintName(dto.getSprintName());
        entity.setGoal(dto.getGoal());
        entity.setProjectId(dto.getProjectId());
        entity.setStatus(dto.getStatus());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        return entity;
    }

    public SprintResponseDTO toDTO(SprintEntity entity){

        SprintResponseDTO dto =new SprintResponseDTO();

        dto.setId(entity.getId());
        dto.setSprintName(entity.getSprintName());
        dto.setGoal(entity.getGoal());
        dto.setProjectId(entity.getProjectId());
        dto.setStatus(entity.getStatus());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

}
