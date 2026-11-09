package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    boolean existsByName(String name);

    Optional<RoleEntity> findByName(String name);
}
