package com.jira_clone.task_management_system.Controller;


import com.jira_clone.task_management_system.Constant.ApiUrlConstant;
import com.jira_clone.task_management_system.DTO.UserRequestDTO;
import com.jira_clone.task_management_system.DTO.UserResponseDTO;
import com.jira_clone.task_management_system.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.USERS)
@Tag(
        name = "User APIs",
        description = "User Management APIs"
)
public class UserController {

    private final UserService userService;

    @PostMapping(ApiUrlConstant.CREATE)
    public ResponseEntity<UserResponseDTO> createUser(
            @Valid @RequestBody UserRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(dto));
    }

    @GetMapping(ApiUrlConstant.GET_BY_ID+"/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(
                userService.getByUserId(userId)
        );
    }

    @GetMapping(ApiUrlConstant.GET_BY_EMAIL+"/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(
            @PathVariable("email") String email) {
        return ResponseEntity.ok(
                userService.getByUserEmail(email)
        );
    }

    @GetMapping(ApiUrlConstant.GET_ALL)
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getAllUsers()
        );
    }

    @PutMapping(ApiUrlConstant.UPDATE+"{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @Valid @PathVariable Long userId,
            @RequestBody UserRequestDTO dto) {
        return ResponseEntity.ok(
                userService.updateUser(userId, dto)
        );
    }

    @DeleteMapping(ApiUrlConstant.DELETE+"/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId) {

        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

}
