package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.SprintRequestDTO;
import com.jira_clone.task_management_system.DTO.SprintResponseDTO;
import com.jira_clone.task_management_system.Entity.SprintEntity;
import com.jira_clone.task_management_system.Service.SprintService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.SPRINTS)
@AllArgsConstructor
@Tag(
        name = "Sprint APIs",
        description = "Sprint Management API"
)
public class SprintController {

    private final SprintService service;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<SprintResponseDTO> createSprint(
            @Valid @RequestBody
            SprintRequestDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    service.createSprint(dto));
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<SprintResponseDTO> getSprintById(
            @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.getSprintById(id)
        );
    }

    @GetMapping(ApiUrlConstant.PROJECT+"/{projectId}")
    public ResponseEntity<List<SprintResponseDTO>> getSprintByProjectId(
            @PathVariable Long projectId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.getByProjectId(projectId)
        );
    }

    @GetMapping(ApiUrlConstant.GET_ALL)
    public  ResponseEntity<List<SprintResponseDTO>> getAllSprint(){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.getAllSprints()
        );
    }

    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<SprintResponseDTO> updateSprint(
            Long id,
            @Valid @RequestBody SprintRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(
                service.updateSprint(id, dto)
        );
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public void deleteSprint(Long id){
       service.deleteSprint(id);
    }
}
