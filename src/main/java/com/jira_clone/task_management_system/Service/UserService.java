package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.UserRequestDTO;
import com.jira_clone.task_management_system.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {

   UserResponseDTO createUser(UserRequestDTO requestDTO);

   UserResponseDTO getByUserId(Long id);

   UserResponseDTO getByUserEmail(String email);

   List<UserResponseDTO> getAllUsers();

   UserResponseDTO updateUser(
           Long id,
           UserRequestDTO requestDTO
   );

   void deleteUser(Long id);
}
