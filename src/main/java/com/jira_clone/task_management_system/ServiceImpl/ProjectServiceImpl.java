package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.ProjectRequestDTO;
import com.jira_clone.task_management_system.DTO.ProjectResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import com.jira_clone.task_management_system.Entity.UserEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.ProjectMapper;
import com.jira_clone.task_management_system.Repository.ProjectRepository;
import com.jira_clone.task_management_system.Repository.UserRepository;
import com.jira_clone.task_management_system.Service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final UserRepository userRepository;
    private final ProjectMapper mapper;

    public ProjectResponseDTO createProject(ProjectRequestDTO dto) {

        if (repository.existsByProjectName(dto.getProjectName())) {
            throw new ResourceNotFoundException("Project name already exists");
        }

//        if (repository.existsByProjectKey(dto.getProjectKey())) {
//            throw new ResourceNotFoundException("Project key already exists");
//        }


        ProjectEntity project = mapper.toEntity(dto);



        UserEntity user = userRepository.findById(dto.getCreatedBy())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        project.setCreatedBy(user.getId());
        ProjectEntity result = repository.save(project);
        String projectKey = "P" + String.format("%04d", result.getId());
        result.setProjectKey(projectKey);
        ProjectEntity updated = repository.save(result);

        return mapper.toDTO(updated);
    }


    public ProjectResponseDTO getByid(Long id) {
        ProjectEntity project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return mapper.toDTO(project);
    }

    public ProjectResponseDTO getByProjectKey(String projectKey) {

        ProjectEntity project = repository.findByProjectKey(projectKey)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found")
                );
        return mapper.toDTO(project);
    }

    public ProjectResponseDTO getByProjectName(String projectName) {
        ProjectEntity project = repository.findByProjectName(projectName)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        return mapper.toDTO(project);
    }

    public List<ProjectResponseDTO> getAllProjects() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ProjectResponseDTO updateProject(Long id, ProjectRequestDTO dto) {
        ProjectEntity project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found")
                );
//        project.setProjectKey(dto.getProjectKey());
        project.setProjectName(dto.getProjectName());
        project.setDescription(dto.getDescription());
        repository.save(project);
        return mapper.toDTO(project);
    }

    @Override
    public void deleteProject(Long id) {
        ProjectEntity project = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
        repository.delete(project);
    }

}
