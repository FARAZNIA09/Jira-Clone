
package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.CommentResponseDTO;
import com.jira_clone.task_management_system.Entity.CommentEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.CommentMapper;
import com.jira_clone.task_management_system.Repository.CommentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void findById_shouldReturnComment_whenCommentExists() {
        Long commentId = 1L;

        CommentEntity comment = new CommentEntity();
        comment.setId(commentId);

        CommentResponseDTO response = new CommentResponseDTO();

        when(commentRepository.findById(commentId))
                .thenReturn(Optional.of(comment));

        when(commentMapper.toResponseDTO(comment))
                .thenReturn(response);

        CommentResponseDTO result = commentService.getCommentById(commentId);

        assertNotNull(result);
        assertSame(response, result);

        verify(commentRepository).findById(commentId);
        verify(commentMapper).toResponseDTO(comment);
    }

    @Test
    void findById_shouldThrowException_whenCommentDoesNotExist() {
        Long commentId = 99L;

        when(commentRepository.findById(commentId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.getCommentById(commentId)
        );

        verify(commentRepository).findById(commentId);
        verify(commentMapper, never()).toResponseDTO(any());
    }

    @Test
    void deleteById_shouldDeleteComment_whenCommentExists() {
        Long commentId = 1L;

        CommentEntity comment = new CommentEntity();
        comment.setId(commentId);

        when(commentRepository.findById(commentId))
                .thenReturn(Optional.of(comment));

        commentService.deleteComment(commentId);

        verify(commentRepository).findById(commentId);
        verify(commentRepository).delete(comment);
    }

    @Test
    void deleteById_shouldThrowException_whenCommentDoesNotExist() {
        Long commentId = 99L;

        when(commentRepository.findById(commentId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> commentService.deleteComment(commentId)
        );

        verify(commentRepository).findById(commentId);
        verify(commentRepository, never()).delete(any());
    }
}
