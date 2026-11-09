package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.RoleRequestDTO;
import com.jira_clone.task_management_system.DTO.RoleResponseDTO;
import com.jira_clone.task_management_system.Service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(
        name = "Role APIs",
        description = "Role  Management API"
)
@RequestMapping(ApiUrlConstant.ROLES)
@AllArgsConstructor
public class RoleController {

    private final RoleService RoleService;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<RoleResponseDTO> createRole(
            @Valid @RequestBody
            RoleRequestDTO RoleRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RoleService.createRole(RoleRequestDTO));
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(
                RoleService.getById(id)
        );
    }

    @GetMapping(ApiUrlConstant.GET_BY_NAME+"/{name}")
    public ResponseEntity<RoleResponseDTO> getRoleByName(@PathVariable String name){
        return ResponseEntity.ok(
                RoleService.getRoleByName(name)
        );
    }

    @GetMapping(ApiUrlConstant.GET_ALL)
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles(){
        return ResponseEntity.ok(
                RoleService.getAllRoles()
        );
    }

    @PutMapping(ApiUrlConstant.UPDATE+"/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id,
                                                      @Valid @RequestBody RoleRequestDTO requestDTO){
        return ResponseEntity.ok(
                RoleService.updateRole(requestDTO, id)
        );
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{id}")
    public ResponseEntity<RoleResponseDTO> deleteRole(@PathVariable Long id){
        RoleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
