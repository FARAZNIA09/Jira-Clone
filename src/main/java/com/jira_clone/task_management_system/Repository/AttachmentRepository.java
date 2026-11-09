package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository
        extends JpaRepository<AttachmentEntity, Long> {

    List<AttachmentEntity> findAllByTaskId(Long taskId);

    List<AttachmentEntity> findAllByUploadedBy(Long uploadedBy);
}