package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.RoleRequestDTO;
import com.jira_clone.task_management_system.DTO.RoleResponseDTO;
import com.jira_clone.task_management_system.Entity.RoleEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.RoleMapper;
import com.jira_clone.task_management_system.Repository.RoleRepository;
import com.jira_clone.task_management_system.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository RoleRepository;
    private final RoleMapper RoleMapper;

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO DTO) {
        if(RoleRepository.existsByName(DTO.getName())){
            throw new ResourceNotFoundException(
                    "Role already exists with name: " + DTO.getName()
            );
        }
        RoleEntity entity = RoleMapper.toEntity(DTO);
        RoleEntity RoleEntity = RoleRepository.save(entity);
        return RoleMapper.toResponseDTO(RoleEntity);
    }

    public List<RoleResponseDTO> getAllRoles() {
        return RoleRepository.findAll()
                .stream()
                .map(RoleMapper::toResponseDTO)
                .toList();
    }

    @Override
    public RoleResponseDTO getById(Long id) {
        RoleEntity entity = RoleRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Role not found for id: " + id
                        )
                );


        return RoleMapper.toResponseDTO(entity);
    }

    @Override
    public RoleResponseDTO getRoleByName(String name){
        RoleEntity entity = RoleRepository.findByName(name)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role not found for name: " + name)
                );

        return RoleMapper.toResponseDTO(entity);
    }

    @Override
    public RoleResponseDTO updateRole(RoleRequestDTO DTO, Long id) {
        RoleEntity existingRole = RoleRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Role not found for id: " + id
                        )
                );

        existingRole.setName(DTO.getName());
        existingRole.setDescription(DTO.getDescription());

        RoleEntity updatedRole =
                RoleRepository.save(existingRole);

        return RoleMapper.toResponseDTO(updatedRole);
    }

    public void deleteRole(Long id) {
        RoleRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Role not found for id: " + id
                        )
                );

        RoleRepository.deleteById(id);
    }

//    @Override
//    public RoleResponseDTO findByName(String name) {
//
//        RoleEntity entity = RoleRepository.findByName(name)
//                .orElseThrow(
//                        () -> new ResourceNotFoundException(
//                                "Role not found with name: " + name
//                        )
//                );
//
//        return RoleMapper.toResponseDTO(entity);
//    }




}
