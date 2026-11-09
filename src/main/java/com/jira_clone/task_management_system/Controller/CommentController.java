package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.CommentRequestDTO;
import com.jira_clone.task_management_system.DTO.CommentResponseDTO;
import com.jira_clone.task_management_system.Service.CommentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.COMMENTS)
@RequiredArgsConstructor
@Tag(
        name = "Comment APIs",
        description = "Comment Management API"
)
public class CommentController {

    private final CommentService commentService;

    // Create comment
    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<CommentResponseDTO> createComment(
            @RequestBody CommentRequestDTO commentRequestDTO) {

        CommentResponseDTO response =
                commentService.createComment(commentRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update comment
    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDTO commentRequestDTO) {

        CommentResponseDTO response =
                commentService.updateComment(id, commentRequestDTO);

        return ResponseEntity.ok(response);
    }

    // Delete comment
    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long id) {

        commentService.deleteComment(id);

        return ResponseEntity.noContent().build();
    }

    // Get all comments
    @GetMapping(ApiUrlConstant.GET_ALL)
    public ResponseEntity<List<CommentResponseDTO>> getAllComments() {

        return ResponseEntity.ok(commentService.getAllComments());
    }

    // Get comment by ID
    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    // Get comments by user ID
    @GetMapping(ApiUrlConstant.USER+"/{userId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                commentService.getCommentByUserId(userId));
    }

    // Get comments by task ID
    @GetMapping(ApiUrlConstant.TASK+"/{taskId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentsByTaskId(
            @PathVariable Long taskId) {

        return ResponseEntity.ok(
                commentService.getCommentByTaskId(taskId));
    }
}
