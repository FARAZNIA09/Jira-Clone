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
import com.jira_clone.task_management_system.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto){
        if(repository.existsByEmail(dto.getEmail())){
            throw new AlreadyExistException("Email already exists");
        }
        if (repository.existsByMobileNumber( dto.getMobileNumber())){
            throw new AlreadyExistException("Mobile number already exists");
        }
        RoleEntity role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role Not Found with Id: " + dto.getRoleId())
                );

        UserEntity user = mapper.toEntity(dto);

        user.setRole(role);

        user.setIsActive(true);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        UserEntity savedUser = repository.save(user);

        return mapper.toDTO(savedUser);
    }

    @Override
    public UserResponseDTO getByUserId(Long id){
        UserEntity user = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with Id: " + id)
                );

        return mapper.toDTO(user);
    }

    @Override
    public UserResponseDTO getByUserEmail(String email){
        UserEntity user = repository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with Email: " + email)
                );
        return mapper.toDTO(user);
    }



    @Override
    public List<UserResponseDTO> getAllUsers(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto){
        UserEntity user = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with Id: " + id)
                );
        if(repository.existsByEmail(dto.getEmail())){
            throw new AlreadyExistException("Email already exists");
        }
        if (repository.existsByMobileNumber( dto.getMobileNumber())){
            throw new AlreadyExistException("Mobile number already exists");
        }

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        RoleEntity role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role Not Found with Id: " + dto.getRoleId())
                );

        user.setRole(role);
        UserEntity savedUser = repository.save(user);
        return mapper.toDTO(savedUser);
    }

    @Override
    public void deleteUser(Long id){
        UserEntity user = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User not found with Id: " + id)
                );
        repository.delete(user);
    }
}
