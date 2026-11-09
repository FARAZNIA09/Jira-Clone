package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.TaskRequestDTO;
import com.jira_clone.task_management_system.DTO.TaskResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import com.jira_clone.task_management_system.Entity.SprintEntity;
import com.jira_clone.task_management_system.Entity.TaskEntity;
import com.jira_clone.task_management_system.Entity.UserEntity;
import com.jira_clone.task_management_system.Enum.TaskStatus;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.TaskMapper;
import com.jira_clone.task_management_system.Repository.ProjectRepository;
import com.jira_clone.task_management_system.Repository.SprintRepository;
import com.jira_clone.task_management_system.Repository.TaskRepository;
import com.jira_clone.task_management_system.Repository.UserRepository;
import com.jira_clone.task_management_system.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectRepository projectRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;

    private boolean isValidStatusTransition(
            TaskStatus current,
            TaskStatus next) {

        if (current == null || next == null) {
            return false;
        }

        return switch (current) {
            case TODO ->
                    next == TaskStatus.IN_PROGRESS;

            case IN_PROGRESS ->
                    next == TaskStatus.TODO
                            || next == TaskStatus.IN_REVIEW;

            case IN_REVIEW ->
                    next == TaskStatus.IN_PROGRESS
                            || next == TaskStatus.TESTING;

            case TESTING ->
                    next == TaskStatus.IN_REVIEW
                            || next == TaskStatus.DONE;

            case DONE -> false;
        };
    }

    @Override
    public TaskResponseDTO changeTaskStatus(
            Long taskId,
            TaskStatus newStatus) {

        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task does not exist"));

        TaskStatus currentStatus = task.getStatus();

        // Allow an unchanged status as a no-op
        if (currentStatus == newStatus) {
            return taskMapper.toDTO(task);
        }

        if (!isValidStatusTransition(currentStatus, newStatus)) {
            throw new IllegalStateException(
                    "Invalid task status transition: "
                            + currentStatus + " -> " + newStatus);
        }

        task.setStatus(newStatus);

        TaskEntity saved = taskRepository.save(task);

        return taskMapper.toDTO(saved);
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
//        if (taskRepository.existsByTaskKey(taskRequestDTO.getTaskKey())) {
//            throw new ResourceNotFoundException("Task already exists");
//        }

        UserEntity assignedUser =
                userRepository.findById(taskRequestDTO.getAssignedTo())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Assigned user not found"));

        ProjectEntity project = projectRepository.findById(taskRequestDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project does not exist"));

        SprintEntity sprint = sprintRepository.findById(taskRequestDTO.getSprintId())
                .orElseThrow(() -> new ResourceNotFoundException("Sprint does not exist"));

        TaskEntity task = taskMapper.toEntity(taskRequestDTO);

        task.setProjectId(project.getId());
        task.setSprintId(sprint.getId());

        TaskEntity saved = taskRepository.saveAndFlush(task);

        String taskKey = "T" + String.format("%04d", saved.getId());

        saved.setTaskKey(taskKey);

        TaskEntity updated = taskRepository.save(saved);

        return taskMapper.toDTO(updated);
           }

    @Override
    public TaskResponseDTO findById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist"));
        return taskMapper.toDTO(task);

    }

    @Override
    public List<TaskResponseDTO> findByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    @Override
    public List<TaskResponseDTO> findBySprintId(Long sprintId) {
        return taskRepository.findBySprintId(sprintId)
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    @Override
    public TaskResponseDTO findByTaskKey(String taskKey) {
       TaskEntity entity = taskRepository.findByTaskKey(taskKey)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist"));
       return taskMapper.toDTO(entity);
    }

    @Override
    public List<TaskResponseDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    @Override
    public TaskResponseDTO updateTask(Long id, TaskRequestDTO taskRequestDTO) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task does not exist"));

//        task.setTaskKey(taskRequestDTO.getTaskKey());
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setProjectId(taskRequestDTO.getProjectId());
        task.setSprintId(taskRequestDTO.getSprintId());
        TaskStatus requestedStatus = taskRequestDTO.getStatus();

        if (requestedStatus != null
                && requestedStatus != task.getStatus()) {

            if (!isValidStatusTransition(
                    task.getStatus(), requestedStatus)) {

                throw new IllegalStateException(
                        "Invalid task status transition: "
                                + task.getStatus() + " -> " + requestedStatus);
            }

            task.setStatus(requestedStatus);
        }
        task.setPriority(taskRequestDTO.getPriority());
        task.setCreatedBy(taskRequestDTO.getCreatedBy());
        task.setAssignedTo(taskRequestDTO.getAssignedTo());
        task.setDueDate(taskRequestDTO.getDueDate());

        TaskEntity saved = taskRepository.save(task);

        return taskMapper.toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }

}
