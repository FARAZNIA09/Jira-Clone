package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.AttachmentRequestDTO;
import com.jira_clone.task_management_system.DTO.AttachmentResponseDTO;

import java.util.List;

public interface AttachmentService {

    AttachmentResponseDTO createAttachment(AttachmentRequestDTO dto);

    AttachmentResponseDTO updateAttachment(Long id, AttachmentRequestDTO dto);

    void deleteAttachment(Long id);

    AttachmentResponseDTO getAttachmentById(Long id);

    List<AttachmentResponseDTO> getAllAttachments();

    List<AttachmentResponseDTO> getAttachmentsByTaskId(Long taskId);

    List<AttachmentResponseDTO> getAttachmentsByUploadedBy(Long uploadedBy);
}