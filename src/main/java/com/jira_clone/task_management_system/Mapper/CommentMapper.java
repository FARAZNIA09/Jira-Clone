package com.jira_clone.task_management_system.Mapper;

import com.jira_clone.task_management_system.DTO.CommentRequestDTO;
import com.jira_clone.task_management_system.DTO.CommentResponseDTO;
import com.jira_clone.task_management_system.Entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentEntity toEntity(CommentRequestDTO dto){

        CommentEntity entity = new CommentEntity();
        entity.setCommentText(dto.getCommentText());
        entity.setTaskId(dto.getTaskId());
        entity.setUserId(dto.getUserId());
        return entity;
    }


    public CommentResponseDTO toResponseDTO(CommentEntity entity) {

        CommentResponseDTO dto = new CommentResponseDTO();

        dto.setId(entity.getId());
        dto.setTaskId(entity.getTaskId());
        dto.setUserId(entity.getUserId());
        dto.setCommentText(entity.getCommentText());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
