package com.jira_clone.task_management_system.Service;


import com.jira_clone.task_management_system.DTO.ProjectRequestDTO;
import com.jira_clone.task_management_system.DTO.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {

    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

    ProjectResponseDTO getByProjectKey(String projectKey);

    ProjectResponseDTO getByid(Long id);

    ProjectResponseDTO getByProjectName(String projectName);

    List<ProjectResponseDTO> getAllProjects();

    ProjectResponseDTO updateProject(Long id, ProjectRequestDTO projectRequestDTO);

    void deleteProject(Long id);
}
