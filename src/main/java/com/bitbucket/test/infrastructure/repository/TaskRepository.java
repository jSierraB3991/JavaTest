package com.bitbucket.test.infrastructure.repository;

import com.bitbucket.test.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
