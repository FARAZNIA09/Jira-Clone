package com.jira_clone.task_management_system.Repository;

import com.jira_clone.task_management_system.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

   boolean existsByEmail(String email);

   Optional<UserEntity> findByEmail(String email);

   boolean existsByMobileNumber(String mobileNumber);

   Optional<UserEntity> findByMobileNumber(String mobileNumber);

}
