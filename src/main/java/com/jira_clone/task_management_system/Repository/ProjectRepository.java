package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository
        extends JpaRepository<ProjectEntity, Long> {

    boolean existsByProjectName(String projectName);

    boolean existsByProjectKey(String projectKey);

    Optional<ProjectEntity> findByProjectKey(String projectKey);

    Optional<ProjectEntity> findByProjectName(String projectName);

    Optional<ProjectEntity> findByid(Long id);
}
