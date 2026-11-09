
package com.jira_clone.task_management_system.Controller;

import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.AuthRequestDTO;
import com.jira_clone.task_management_system.DTO.AuthResponseDTO;
import com.jira_clone.task_management_system.Service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.AUTH)
@Tag(
        name = "Auth APIs"
)
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiUrlConstant.LOGIN)
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody AuthRequestDTO authRequestDTO) {

        AuthResponseDTO response = authService.login(authRequestDTO);

        return ResponseEntity.ok(response);
    }
}
