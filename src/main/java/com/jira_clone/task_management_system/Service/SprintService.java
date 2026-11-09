package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.SprintRequestDTO;
import com.jira_clone.task_management_system.DTO.SprintResponseDTO;

import java.util.List;

public interface SprintService {

    SprintResponseDTO createSprint(SprintRequestDTO dto);

    SprintResponseDTO updateSprint(Long id, SprintRequestDTO dto);

    void deleteSprint(Long id);

    List<SprintResponseDTO> getByProjectId(Long projectId);

    List<SprintResponseDTO> getAllSprints();

    SprintResponseDTO getSprintById(Long id);
}
