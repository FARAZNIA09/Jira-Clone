package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.TaskRequestDTO;
import com.jira_clone.task_management_system.DTO.TaskResponseDTO;
import com.jira_clone.task_management_system.Service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.TASKS)
@Tag(
        name = "Task APIs",
        description = "Task Management API"
)
public class TaskController {
    
    private final TaskService taskService;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody 
            TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(taskRequestDTO)
        );
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(
                taskService.findById(id));
    }

    @GetMapping(ApiUrlConstant.TASK+"/{taskKey}")
    public ResponseEntity<TaskResponseDTO> findTaskByKey(@PathVariable String taskKey) {
        return ResponseEntity.ok(
                taskService.findByTaskKey(taskKey)
        );
    }

    @GetMapping(ApiUrlConstant.GET_ALL)
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
        return ResponseEntity.ok(
                taskService.findAll());
    }

    @GetMapping(ApiUrlConstant.PROJECT+"/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> findByProjectId(@PathVariable Long projectId) {
        return ResponseEntity.ok(
                taskService.findByProjectId(projectId)
        );
    }

    @GetMapping(ApiUrlConstant.SPRINT+"/{sprintId}")
    public ResponseEntity<List<TaskResponseDTO>> findBySprintId(@PathVariable Long sprintId) {
        return ResponseEntity.ok(
                taskService.findBySprintId(sprintId)
        );
    }

    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.ok(
                taskService.updateTask(id, taskRequestDTO)
        );
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public ResponseEntity<Void> deleteTaskById(
            @PathVariable Long id) {

        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
