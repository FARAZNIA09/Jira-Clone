package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.AttachmentRequestDTO;
import com.jira_clone.task_management_system.DTO.AttachmentResponseDTO;
import com.jira_clone.task_management_system.Entity.AttachmentEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.AttachmentMapper;
import com.jira_clone.task_management_system.Repository.AttachmentRepository;
import com.jira_clone.task_management_system.Repository.TaskRepository;
import com.jira_clone.task_management_system.Repository.UserRepository;
import com.jira_clone.task_management_system.Service.AttachmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public AttachmentResponseDTO createAttachment(AttachmentRequestDTO dto) {

        taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        userRepository.findById(dto.getUploadedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        AttachmentEntity entity = attachmentMapper.toEntity(dto);

        return attachmentMapper.toResponseDTO(
                attachmentRepository.save(entity));
    }

    @Override
    public AttachmentResponseDTO updateAttachment(Long id, AttachmentRequestDTO dto) {

        AttachmentEntity entity = attachmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found"));

        taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        userRepository.findById(dto.getUploadedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        entity.setTaskId(dto.getTaskId());
        entity.setFileName(dto.getFileName());
        entity.setFilePath(dto.getFilePath());
        entity.setUploadedBy(dto.getUploadedBy());

        return attachmentMapper.toResponseDTO(
                attachmentRepository.save(entity));
    }

    @Override
    public void deleteAttachment(Long id) {
        AttachmentEntity entity = attachmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attachment not found"));

        attachmentRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public AttachmentResponseDTO getAttachmentById(Long id) {
        return attachmentMapper.toResponseDTO(
                attachmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Attachment not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentResponseDTO> getAllAttachments() {
        return attachmentRepository.findAll()
                .stream()
                .map(attachmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentResponseDTO> getAttachmentsByTaskId(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return attachmentRepository.findAllByTaskId(taskId)
                .stream()
                .map(attachmentMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentResponseDTO> getAttachmentsByUploadedBy(Long uploadedBy) {
        userRepository.findById(uploadedBy)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return attachmentRepository.findAllByUploadedBy(uploadedBy)
                .stream()
                .map(attachmentMapper::toResponseDTO)
                .toList();
    }
}