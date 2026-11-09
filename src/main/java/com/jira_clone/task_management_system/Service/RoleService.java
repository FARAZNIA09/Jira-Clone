package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.RoleRequestDTO;
import com.jira_clone.task_management_system.DTO.RoleResponseDTO;

import java.util.List;

public interface RoleService {


   RoleResponseDTO getById(Long id);

   RoleResponseDTO createRole(RoleRequestDTO RoleRequestDTO);

//    RoleResponseDTO findByName(String name);

    List<RoleResponseDTO> getAllRoles();

    RoleResponseDTO getRoleByName(String name);

    RoleResponseDTO updateRole(RoleRequestDTO RoleRequestDTO, Long id);

    void deleteRole(Long id);

}
