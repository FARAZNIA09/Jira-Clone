package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.CommentRequestDTO;
import com.jira_clone.task_management_system.DTO.CommentResponseDTO;
import com.jira_clone.task_management_system.Entity.CommentEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.CommentMapper;
import com.jira_clone.task_management_system.Repository.CommentRepository;
import com.jira_clone.task_management_system.Repository.TaskRepository;
import com.jira_clone.task_management_system.Repository.UserRepository;
import com.jira_clone.task_management_system.Service.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDTO createComment(
            CommentRequestDTO commentRequestDTO) {

        // Validate that the task exists
        taskRepository.findById(commentRequestDTO.getTaskId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        // Validate that the user exists
        userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Convert request DTO into entity
        CommentEntity comment =
                commentMapper.toEntity(commentRequestDTO);

        // Save comment in the database
        CommentEntity savedComment =
                commentRepository.save(comment);

        // Convert saved entity into response DTO
        return commentMapper.toResponseDTO(savedComment);
    }

    @Override
    public CommentResponseDTO updateComment(
            Long id,
            CommentRequestDTO commentRequestDTO) {

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        taskRepository.findById(commentRequestDTO.getTaskId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Update the existing entity
        comment.setTaskId(commentRequestDTO.getTaskId());
        comment.setUserId(commentRequestDTO.getUserId());
        comment.setCommentText(commentRequestDTO.getCommentText());

        CommentEntity updatedComment =
                commentRepository.save(comment);

        return commentMapper.toResponseDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getAllComments() {

        return commentRepository.findAll()
                .stream()
                .map(commentMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CommentResponseDTO getCommentById(Long id) {

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found"));

        return commentMapper.toResponseDTO(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getCommentByUserId(Long id) {

        userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return commentRepository.findAllByUserId(id)
                .stream()
                .map(commentMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDTO> getCommentByTaskId(Long id) {

        taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found"));

        return commentRepository.findAllByTaskId(id)
                .stream()
                .map(commentMapper::toResponseDTO)
                .toList();
    }
}
