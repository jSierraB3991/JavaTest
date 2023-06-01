package com.bitbucket.test.infrastructure.repository;

import com.bitbucket.test.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {

    @Query("SELECT t FROM Task t WHERE user.code = :userCode")
    List<Task> findAllByUser(String userCode);
}
