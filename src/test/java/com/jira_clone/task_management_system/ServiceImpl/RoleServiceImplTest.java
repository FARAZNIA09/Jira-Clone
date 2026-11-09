
package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.RoleResponseDTO;
import com.jira_clone.task_management_system.Entity.RoleEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.RoleMapper;
import com.jira_clone.task_management_system.Repository.RoleRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void findById_shouldReturnRole_whenRoleExists() {
        Long roleId = 1L;

        RoleEntity role = new RoleEntity();
        role.setId(roleId);

        RoleResponseDTO response = new RoleResponseDTO();

        when(roleRepository.findById(roleId))
                .thenReturn(Optional.of(role));

        when(roleMapper.toResponseDTO(role))
                .thenReturn(response);

        RoleResponseDTO result = roleService.getById(roleId);

        assertNotNull(result);
        assertSame(response, result);

        verify(roleRepository).findById(roleId);
        verify(roleMapper).toResponseDTO(role);
    }

    @Test
    void findById_shouldThrowException_whenRoleDoesNotExist() {
        Long roleId = 99L;

        when(roleRepository.findById(roleId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> roleService.getById(roleId)
        );

        verify(roleRepository).findById(roleId);
        verify(roleMapper, never()).toResponseDTO(any());
    }


    @Test
    void deleteById_shouldDeleteRole_whenRoleExists() {
        Long roleId = 1L;

        RoleEntity role = new RoleEntity();
        role.setId(roleId);

        when(roleRepository.findById(roleId))
                .thenReturn(Optional.of(role));

        roleService.deleteRole(roleId);

        verify(roleRepository).findById(roleId);
        verify(roleRepository).deleteById(roleId);
    }


    @Test
    void deleteById_shouldThrowException_whenRoleDoesNotExist() {
        Long roleId = 99L;

        when(roleRepository.findById(roleId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> roleService.deleteRole(roleId)
        );

        verify(roleRepository).findById(roleId);
        verify(roleRepository, never()).delete(any());
    }
}
