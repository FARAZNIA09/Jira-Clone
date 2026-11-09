
package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.AttachmentResponseDTO;
import com.jira_clone.task_management_system.Entity.AttachmentEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.AttachmentMapper;
import com.jira_clone.task_management_system.Repository.AttachmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttachmentServiceImplTest {

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private AttachmentMapper attachmentMapper;

    @InjectMocks
    private AttachmentServiceImpl attachmentService;

    @Test
    void findById_shouldReturnAttachment_whenAttachmentExists() {
        Long attachmentId = 1L;

        AttachmentEntity attachment = new AttachmentEntity();
        attachment.setId(attachmentId);

        AttachmentResponseDTO response = new AttachmentResponseDTO();

        when(attachmentRepository.findById(attachmentId))
                .thenReturn(Optional.of(attachment));

        when(attachmentMapper.toResponseDTO(attachment))
                .thenReturn(response);

        AttachmentResponseDTO result =
                attachmentService.getAttachmentById(attachmentId);

        assertNotNull(result);
        assertSame(response, result);

        verify(attachmentRepository).findById(attachmentId);
        verify(attachmentMapper).toResponseDTO(attachment);
    }

    @Test
    void findById_shouldThrowException_whenAttachmentDoesNotExist() {
        Long attachmentId = 99L;

        when(attachmentRepository.findById(attachmentId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> attachmentService.getAttachmentById(attachmentId)
        );

        verify(attachmentRepository).findById(attachmentId);
        verify(attachmentMapper, never()).toResponseDTO(any());
    }

    @Test
    void deleteById_shouldDeleteAttachment_whenAttachmentExists() {
        Long attachmentId = 1L;

        AttachmentEntity attachment = new AttachmentEntity();
        attachment.setId(attachmentId);

        when(attachmentRepository.findById(attachmentId))
                .thenReturn(Optional.of(attachment));

        attachmentService.deleteAttachment(attachmentId);

        verify(attachmentRepository).findById(attachmentId);
        verify(attachmentRepository).delete(attachment);
    }

    @Test
    void deleteById_shouldThrowException_whenAttachmentDoesNotExist() {
        Long attachmentId = 99L;

        when(attachmentRepository.findById(attachmentId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> attachmentService.deleteAttachment(attachmentId)
        );

        verify(attachmentRepository).findById(attachmentId);
        verify(attachmentRepository, never()).delete(any());
    }
}
