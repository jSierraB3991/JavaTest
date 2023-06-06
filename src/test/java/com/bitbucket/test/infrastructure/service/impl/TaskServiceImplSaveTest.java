package com.bitbucket.test.infrastructure.service.impl;

import com.bitbucket.test.domain.mapper.TaskMapperMock;
import com.bitbucket.test.domain.mapper.UserMapperMock;
import com.bitbucket.test.domain.model.TaskState;
import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.repository.TaskRepository;
import com.bitbucket.test.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

@DataJpaTest
public class TaskServiceImplSaveTest {
    private final TaskServiceImpl service;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImplSaveTest(TaskRepository repository, UserRepository userRepository) {
        var userService = new UserServiceImpl(userRepository, new UserMapperMock());
        this.service = new TaskServiceImpl(repository, new TaskMapperMock(), userService);
        this.taskRepository = repository;
        this.userRepository = userRepository;
    }

    @Test
    public void saveSuccess() {
        final String nameTask = "Task1";
        final String userCode = userRepository.save(User.builder()
                .code(UUID.randomUUID().toString())
                .lastName("Doe")
                .name("jon").build()).getCode();

        var response = service.save(TaskRequest.builder().active(true).name(nameTask).userCode(userCode).build());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        var taskResponse = service.findByCode(response.getResponse().getCode());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());
        Assertions.assertTrue(taskResponse.getResponse().getActive());
        Assertions.assertEquals(taskResponse.getResponse().getName(), nameTask);
        Assertions.assertEquals(taskResponse.getResponse().getState(), TaskState.CREATED);
    }
}
