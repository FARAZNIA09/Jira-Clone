package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.SprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SprintRepository extends
                    JpaRepository<SprintEntity,Long> {

    boolean existsBySprintName(String sprintName);

    List<SprintEntity> findByProjectId(Long projectId);

    Optional<SprintEntity> findBySprintName(String sprintName);;
}
