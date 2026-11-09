package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.CommentRequestDTO;
import com.jira_clone.task_management_system.DTO.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO);

    CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO);

    void deleteComment(Long id);

    List<CommentResponseDTO> getAllComments();

    CommentResponseDTO getCommentById(Long id);

    List<CommentResponseDTO> getCommentByUserId(Long id);

    List<CommentResponseDTO> getCommentByTaskId(Long id);
}
