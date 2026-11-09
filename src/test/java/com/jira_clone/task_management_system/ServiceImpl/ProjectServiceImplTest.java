package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.ProjectRequestDTO;
import com.jira_clone.task_management_system.DTO.ProjectResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.ProjectMapper;
import com.jira_clone.task_management_system.Repository.ProjectRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void findById_shouldReturnProject_whenProjectExists() {

        // Arrange
        Long projectId = 1L;

        ProjectEntity project = new ProjectEntity();
        project.setId(projectId);

        ProjectResponseDTO response = new ProjectResponseDTO();

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));

        when(projectMapper.toDTO(project))
                .thenReturn(response);

        // Act
        ProjectResponseDTO result = projectService.getByid(projectId);

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(projectRepository).findById(projectId);
        verify(projectMapper).toDTO(project);
    }

    @Test
    void findById_shouldThrowException_whenProjectDoesNotExist() {

        // Arrange
        Long projectId = 99L;

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> projectService.getByid(projectId)
        );

        verify(projectRepository).findById(projectId);
        verify(projectMapper, never()).toDTO(any());
    }

    @Test
    void deleteById_shouldDeleteProject_whenProjectExists() {

        // Arrange
        Long projectId = 1L;

        ProjectEntity project = new ProjectEntity();
        project.setId(projectId);

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));

        // Act
        projectService.deleteProject(projectId);

        // Assert
        verify(projectRepository).findById(projectId);
        verify(projectRepository).delete(project);
    }

    @Test
    void deleteById_shouldThrowException_whenProjectDoesNotExist() {

        // Arrange
        Long projectId = 99L;

        when(projectRepository.findById(projectId))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> projectService.deleteProject(projectId)
        );

        verify(projectRepository, never()).delete(any());
    }
}