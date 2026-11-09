package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.ProjectRequestDTO;
import com.jira_clone.task_management_system.DTO.ProjectResponseDTO;
import com.jira_clone.task_management_system.Service.ProjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUrlConstant.PROJECTS)
@RequiredArgsConstructor
@Tag(
        name = "Project APIs",
        description = "Project Management API"
)
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody
            ProjectRequestDTO projectRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectService.createProject(projectRequestDTO));
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(
                projectService.getByid(id));
    }

    @GetMapping(ApiUrlConstant.PROJECT+"/{projectName}")
    public ResponseEntity<ProjectResponseDTO> getProjectByName(@PathVariable String projectName) {
        return ResponseEntity.ok(
                projectService.getByProjectName(projectName));
    }

    @GetMapping(ApiUrlConstant.PROJECT+"/{projectKey}")
    public ResponseEntity<ProjectResponseDTO> getProjectByKey(@PathVariable String projectKey) {
        return ResponseEntity.ok(
                projectService.getByProjectKey(projectKey));
    }

    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<ProjectResponseDTO>
                        updateProject(@PathVariable Long id,
                                     @Valid @RequestBody ProjectRequestDTO projectRequestDTO) {
        return ResponseEntity.ok(projectService.updateProject(id, projectRequestDTO));
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

}
