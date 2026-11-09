package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.UserRequestDTO;
import com.jira_clone.task_management_system.DTO.UserResponseDTO;
import com.jira_clone.task_management_system.Entity.RoleEntity;
import com.jira_clone.task_management_system.Entity.UserEntity;
import com.jira_clone.task_management_system.Exception.AlreadyExistException;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.UserMapper;
import com.jira_clone.task_management_system.Repository.RoleRepository;
import com.jira_clone.task_management_system.Repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequestDTO requestDTO;
    private UserEntity user;
    private UserResponseDTO responseDTO;
    private RoleEntity role;

    @BeforeEach
    void setUp() {

        requestDTO = UserRequestDTO.builder()
                .firstName("Faraz")
                .lastName("Niyazi")
                .email("faraz@gmail.com")
                .password("password123")
                .mobileNumber("9876543210")
                .roleId(1L)
                .build();

        role = mock(RoleEntity.class);

        user = UserEntity.builder()
                .id(1L)
                .firstName("Faraz")
                .lastName("Niyazi")
                .email("faraz@gmail.com")
                .password("encodedPassword")
                .mobileNumber("9876543210")
                .role(role)
                .isActive(true)
                .build();

        responseDTO = UserResponseDTO.builder()
                .id(1L)
                .firstName("Faraz")
                .lastName("Niyazi")
                .email("faraz@gmail.com")
                .mobileNumber("9876543210")
                .isActive(true)
                .build();
    }

    // TEST 1: Create user successfully
    @Test
    void createUser_shouldReturnUserResponse_whenValidDataProvided() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(false);

        when(repository.existsByMobileNumber(requestDTO.getMobileNumber()))
                .thenReturn(false);

        when(roleRepository.findById(1L))
                .thenReturn(Optional.of(role));
        user.setPassword("password123");

        when(mapper.toEntity(requestDTO))
                .thenReturn(user);

        when(passwordEncoder.encode("password123"))
                .thenReturn("encodedPassword");

        when(repository.save(user))
                .thenReturn(user);

        when(mapper.toDTO(user))
                .thenReturn(responseDTO);

        UserResponseDTO result = userService.createUser(requestDTO);

        assertNotNull(result);
        assertEquals("Faraz", result.getFirstName());
        assertEquals("faraz@gmail.com", result.getEmail());

        verify(repository).save(user);
        verify(passwordEncoder).encode("password123");
    }

    // TEST 2: Duplicate email
    @Test
    void createUser_shouldThrowException_whenEmailAlreadyExists() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(true);

        assertThrows(
                AlreadyExistException.class,
                () -> userService.createUser(requestDTO)
        );

        verify(repository, never()).save(any());
    }

    // TEST 3: Duplicate mobile number
    @Test
    void createUser_shouldThrowException_whenMobileAlreadyExists() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(false);

        when(repository.existsByMobileNumber(requestDTO.getMobileNumber()))
                .thenReturn(true);

        assertThrows(
                AlreadyExistException.class,
                () -> userService.createUser(requestDTO)
        );

        verify(repository, never()).save(any());
    }

    // TEST 4: Role does not exist
    @Test
    void createUser_shouldThrowException_whenRoleDoesNotExist() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(false);

        when(repository.existsByMobileNumber(requestDTO.getMobileNumber()))
                .thenReturn(false);

        when(roleRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.createUser(requestDTO)
        );

        verify(repository, never()).save(any());
    }

    // TEST 5: Get user by ID
    @Test
    void getByUserId_shouldReturnUser_whenUserExists() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        when(mapper.toDTO(user))
                .thenReturn(responseDTO);

        UserResponseDTO result = userService.getByUserId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(repository).findById(1L);
    }

    // TEST 6: User not found by ID
    @Test
    void getByUserId_shouldThrowException_whenUserDoesNotExist() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getByUserId(1L)
        );

        verify(mapper, never()).toDTO(any());
    }

    // TEST 7: Get user by email
    @Test
    void getByUserEmail_shouldReturnUser_whenUserExists() {

        when(repository.findByEmail("faraz@gmail.com"))
                .thenReturn(Optional.of(user));

        when(mapper.toDTO(user))
                .thenReturn(responseDTO);

        UserResponseDTO result =
                userService.getByUserEmail("faraz@gmail.com");

        assertNotNull(result);
        assertEquals("faraz@gmail.com", result.getEmail());
    }

    // TEST 8: Get all users
    @Test
    void getAllUsers_shouldReturnUserList() {

        when(repository.findAll())
                .thenReturn(List.of(user));

        when(mapper.toDTO(user))
                .thenReturn(responseDTO);

        List<UserResponseDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Faraz", result.get(0).getFirstName());
    }

    // TEST 9: Get all users when database is empty
    @Test
    void getAllUsers_shouldReturnEmptyList_whenNoUsersExist() {

        when(repository.findAll())
                .thenReturn(List.of());

        List<UserResponseDTO> result = userService.getAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // TEST 10: Delete user successfully
    @Test
    void deleteUser_shouldDeleteUser_whenUserExists() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(repository).delete(user);
    }

    // TEST 11: Delete user when user does not exist
    @Test
    void deleteUser_shouldThrowException_whenUserDoesNotExist() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.deleteUser(1L)
        );

        verify(repository, never()).delete(any());
    }
}
