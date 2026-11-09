
package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.AuthRequestDTO;
import com.jira_clone.task_management_system.DTO.AuthResponseDTO;

public interface AuthService {

    AuthResponseDTO login(AuthRequestDTO authRequestDTO);

}
