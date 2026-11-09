package com.jira_clone.task_management_system.Service;

import com.jira_clone.task_management_system.DTO.TaskRequestDTO;
import com.jira_clone.task_management_system.DTO.TaskResponseDTO;
import com.jira_clone.task_management_system.Enum.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO dto);

    TaskResponseDTO updateTask(Long id, TaskRequestDTO dto);

    void deleteById(Long id);

    TaskResponseDTO findById(Long id);

    TaskResponseDTO findByTaskKey(String taskKey);

    List<TaskResponseDTO> findAll();

    List<TaskResponseDTO> findByProjectId(Long projectId);

    List<TaskResponseDTO> findBySprintId(Long sprintId);

    TaskResponseDTO changeTaskStatus(Long taskId, TaskStatus newStatus);
}
