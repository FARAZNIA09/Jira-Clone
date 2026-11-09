package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByUserId(Long id);

    List<CommentEntity> findAllByTaskId(Long id);

}
