package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    boolean existsByTaskKey(String taskKey);

    Optional<TaskEntity> findByTaskKey(String taskKey);

    List<TaskEntity> findByProjectId(Long projectId);

    List<TaskEntity> findBySprintId(Long sprintId);

    List<TaskEntity> findByAssignedTo(Long assignedTo);
}
