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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private SprintRepository sprintRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    // 1. Create task successfully
    @Test
    void createTask_shouldGenerateTaskKey() {

        TaskRequestDTO request = new TaskRequestDTO();
        request.setAssignedTo(2L);
        request.setProjectId(3L);
        request.setSprintId(4L);

        UserEntity user = new UserEntity();

        ProjectEntity project = new ProjectEntity();
        project.setId(3L);

        SprintEntity sprint = new SprintEntity();
        sprint.setId(4L);

        TaskEntity task = new TaskEntity();
        TaskResponseDTO response = new TaskResponseDTO();

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(user));

        when(projectRepository.findById(3L))
                .thenReturn(Optional.of(project));

        when(sprintRepository.findById(4L))
                .thenReturn(Optional.of(sprint));

        when(taskMapper.toEntity(request))
                .thenReturn(task);

        when(taskRepository.saveAndFlush(task))
                .thenAnswer(invocation -> {
                    task.setId(1L);
                    return task;
                });

        when(taskRepository.save(task))
                .thenReturn(task);

        when(taskMapper.toDTO(task))
                .thenReturn(response);

        TaskResponseDTO result = taskService.createTask(request);

        assertNotNull(result);
        assertEquals("T0001", task.getTaskKey());

        verify(taskRepository).saveAndFlush(task);
        verify(taskRepository).save(task);
    }

    // 2. Assigned user does not exist
    @Test
    void createTask_shouldThrowWhenUserNotFound() {

        TaskRequestDTO request = new TaskRequestDTO();
        request.setAssignedTo(99L);

        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.createTask(request)
        );

        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    // 3. Valid status transition
    @Test
    void changeTaskStatus_shouldAllowValidTransition() {

        TaskEntity task = new TaskEntity();
        task.setId(1L);
        task.setStatus(TaskStatus.TODO);

        TaskResponseDTO response = new TaskResponseDTO();

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(task))
                .thenReturn(task);

        when(taskMapper.toDTO(task))
                .thenReturn(response);

        TaskResponseDTO result =
                taskService.changeTaskStatus(
                        1L, TaskStatus.IN_PROGRESS);

        assertNotNull(result);
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());

        verify(taskRepository).save(task);
    }

    // 4. Invalid status transition
    @Test
    void changeTaskStatus_shouldRejectInvalidTransition() {

        TaskEntity task = new TaskEntity();
        task.setId(1L);
        task.setStatus(TaskStatus.TODO);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        assertThrows(
                IllegalStateException.class,
                () -> taskService.changeTaskStatus(
                        1L, TaskStatus.DONE)
        );

        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    // 5. Task does not exist
    @Test
    void findById_shouldThrowWhenTaskNotFound() {

        when(taskRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.findById(99L)
        );

        verify(taskMapper, never()).toDTO(any(TaskEntity.class));
    }
}