package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.SprintResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import com.jira_clone.task_management_system.Entity.SprintEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.SprintMapper;
import com.jira_clone.task_management_system.Repository.ProjectRepository;
import com.jira_clone.task_management_system.Repository.SprintRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintServiceImplTest {

    @Mock
    private SprintRepository sprintRepository;

    @Mock
    private SprintMapper sprintMapper;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private SprintServiceImpl sprintService;

    @Test
    void findById_shouldReturnSprint_whenSprintExists() {

        // Arrange
        Long sprintId = 1L;

        SprintEntity sprint = new SprintEntity();
        sprint.setId(sprintId);

        SprintResponseDTO response = new SprintResponseDTO();

        when(sprintRepository.findById(sprintId))
                .thenReturn(Optional.of(sprint));

        when(sprintMapper.toDTO(sprint))
                .thenReturn(response);

        // Act
        SprintResponseDTO result = sprintService.getSprintById(sprintId);

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(sprintRepository).findById(sprintId);
        verify(sprintMapper).toDTO(sprint);
    }

    @Test
    void findById_shouldThrowException_whenSprintDoesNotExist() {

        // Arrange
        Long sprintId = 99L;

        when(sprintRepository.findById(sprintId))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> sprintService.getSprintById(sprintId)
        );

        verify(sprintRepository).findById(sprintId);
        verify(sprintMapper, never()).toDTO(any());
    }

    @Test
    void deleteById_shouldDeleteSprint_whenSprintExists() {

        // Arrange
        Long sprintId = 1L;

        SprintEntity sprint = new SprintEntity();
        sprint.setId(sprintId);

        when(sprintRepository.findById(sprintId))
                .thenReturn(Optional.of(sprint));

        // Act
        sprintService.deleteSprint(sprintId);

        // Assert
        verify(sprintRepository).findById(sprintId);
        verify(sprintRepository).delete(sprint);
    }

    @Test
    void deleteById_shouldThrowException_whenSprintDoesNotExist() {

        // Arrange
        Long sprintId = 99L;

        when(sprintRepository.findById(sprintId))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                ResourceNotFoundException.class,
                () -> sprintService.deleteSprint(sprintId)
        );

        verify(sprintRepository, never()).delete(any());
    }
}