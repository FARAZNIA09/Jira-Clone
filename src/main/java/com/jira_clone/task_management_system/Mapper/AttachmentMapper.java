package com.jira_clone.task_management_system.Mapper;

import com.jira_clone.task_management_system.DTO.AttachmentRequestDTO;
import com.jira_clone.task_management_system.DTO.AttachmentResponseDTO;
import com.jira_clone.task_management_system.Entity.AttachmentEntity;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

    public AttachmentEntity toEntity(AttachmentRequestDTO dto) {
        AttachmentEntity entity = new AttachmentEntity();

        entity.setTaskId(dto.getTaskId());
        entity.setFileName(dto.getFileName());
        entity.setFilePath(dto.getFilePath());
        entity.setUploadedBy(dto.getUploadedBy());

        return entity;
    }

    public AttachmentResponseDTO toResponseDTO(AttachmentEntity entity) {
        AttachmentResponseDTO dto = new AttachmentResponseDTO();

        dto.setId(entity.getId());
        dto.setTaskId(entity.getTaskId());
        dto.setFileName(entity.getFileName());
        dto.setFilePath(entity.getFilePath());
        dto.setUploadedBy(entity.getUploadedBy());
        dto.setUploadedAt(entity.getUploadedAt());

        return dto;
    }
}