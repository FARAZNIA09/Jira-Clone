package com.jira_clone.task_management_system.ServiceImpl;

import com.jira_clone.task_management_system.DTO.SprintRequestDTO;
import com.jira_clone.task_management_system.DTO.SprintResponseDTO;
import com.jira_clone.task_management_system.Entity.ProjectEntity;
import com.jira_clone.task_management_system.Entity.SprintEntity;
import com.jira_clone.task_management_system.Exception.ResourceNotFoundException;
import com.jira_clone.task_management_system.Mapper.SprintMapper;
import com.jira_clone.task_management_system.Repository.ProjectRepository;
import com.jira_clone.task_management_system.Repository.SprintRepository;
import com.jira_clone.task_management_system.Service.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository repository;
    private final SprintMapper mapper;
    private final ProjectRepository projectRepository;

    @Override
    public SprintResponseDTO createSprint(SprintRequestDTO dto) {

        SprintEntity entity = mapper.toEntity(dto);

        ProjectEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        entity.setProjectId(project.getId());

      SprintEntity result = repository.save(entity);

      return mapper.toDTO(result);
    }

    @Override
    public List<SprintResponseDTO> getByProjectId(Long projectId) {
        return repository.findByProjectId(projectId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }


    @Override
    public List<SprintResponseDTO> getAllSprints() {
    return repository.findAll()
            .stream()
            .map(mapper::toDTO)
            .toList();
    }

    @Override
    public SprintResponseDTO getSprintById(Long id) {
        SprintEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        return mapper.toDTO(entity);
    }

    @Override
    public SprintResponseDTO updateSprint(Long id, SprintRequestDTO dto) {
        SprintEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        entity.setSprintName(dto.getSprintName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setProjectId(dto.getProjectId());
        entity.setStatus(dto.getStatus());
        entity.setGoal(dto.getGoal());

        ProjectEntity project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        entity.setProjectId(project.getId());

        SprintEntity result = repository.save(entity);

        return mapper.toDTO(result);
    }

    @Override
    public void deleteSprint(Long id) {
        SprintEntity entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        repository.delete(entity);
    }

}
