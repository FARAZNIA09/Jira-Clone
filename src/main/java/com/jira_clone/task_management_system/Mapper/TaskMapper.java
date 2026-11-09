package com.jira_clone.task_management_system.Mapper;

import com.jira_clone.task_management_system.DTO.TaskRequestDTO;
import com.jira_clone.task_management_system.DTO.TaskResponseDTO;
import com.jira_clone.task_management_system.Entity.TaskEntity;
import org.springframework.stereotype.Component;



@Component
public class TaskMapper {

    public TaskEntity toEntity(TaskRequestDTO dto){
        TaskEntity entity = new TaskEntity();

        entity.setTitle(dto.getTitle());
//        entity.setTaskKey(dto.getTaskKey());
        entity.setDescription(dto.getDescription());
        entity.setSprintId(dto.getSprintId());
        entity.setAssignedTo(dto.getAssignedTo());
        entity.setProjectId(dto.getProjectId());
        entity.setPriority(dto.getPriority());
        entity.setDueDate(dto.getDueDate());
        entity.setStatus(dto.getStatus());
        entity.setCreatedBy(dto.getCreatedBy());
        return entity;
    }

    public TaskResponseDTO toDTO(TaskEntity entity){
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setTaskKey(entity.getTaskKey());
        dto.setDescription(entity.getDescription());
        dto.setSprintId(entity.getSprintId());
        dto.setAssignedTo(entity.getAssignedTo());
        dto.setProjectId(entity.getProjectId());
        dto.setPriority(entity.getPriority());
        dto.setDueDate(entity.getDueDate());
        dto.setStatus(entity.getStatus());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getCreatedAt());
        return dto;
    }
}
