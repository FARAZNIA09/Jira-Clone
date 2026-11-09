package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.AttachmentRequestDTO;
import com.jira_clone.task_management_system.DTO.AttachmentResponseDTO;
import com.jira_clone.task_management_system.Service.AttachmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.ATTACHMENTS)
@RequiredArgsConstructor
@Tag(
        name = "Attachment APIs"
)
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<AttachmentResponseDTO> createAttachment(
            @RequestBody AttachmentRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attachmentService.createAttachment(dto));
    }

    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<AttachmentResponseDTO> updateAttachment(
            @PathVariable Long id,
            @RequestBody AttachmentRequestDTO dto) {
        return ResponseEntity.ok(attachmentService.updateAttachment(id, dto));
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Long id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ApiUrlConstant.GET_ALL)
    public ResponseEntity<List<AttachmentResponseDTO>> getAllAttachments() {
        return ResponseEntity.ok(attachmentService.getAllAttachments());
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<AttachmentResponseDTO> getAttachmentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(attachmentService.getAttachmentById(id));
    }

    @GetMapping(ApiUrlConstant.TASK+"/{taskId}")
    public ResponseEntity<List<AttachmentResponseDTO>> getByTaskId(
            @PathVariable Long taskId) {
        return ResponseEntity.ok(attachmentService.getAttachmentsByTaskId(taskId));
    }

    @GetMapping(ApiUrlConstant.USER+"/{uploadedBy}")
    public ResponseEntity<List<AttachmentResponseDTO>> getByUploadedBy(
            @PathVariable Long uploadedBy) {
        return ResponseEntity.ok(attachmentService.getAttachmentsByUploadedBy(uploadedBy));
    }
}