package com.bitbucket.test.infrastructure.service.impl;

import com.bitbucket.test.domain.mapper.TaskMapper;
import com.bitbucket.test.domain.model.Task;
import com.bitbucket.test.domain.model.TaskState;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;
import com.bitbucket.test.infrastructure.repository.TaskRepository;
import com.bitbucket.test.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {
    @InjectMocks
    private TaskServiceImpl service;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private TaskMapper taskMapper;

    @Test
    public void updateBad_whenSendStateDoneAndActiveIsFalse() {
        when(taskRepository.findById(anyString()))
                .thenReturn(Optional.of(Task.builder().active(false).build()));
        when(taskMapper.toResponse(any(Task.class)))
                .thenReturn(TaskResponse.builder().name("task1").active(false).build());

        final var taskUpdate = TaskUpdateRequest.builder().state(TaskState.DONE).build();
        final String code = UUID.randomUUID().toString();

        var response = service.update(taskUpdate, code);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }

    @Test
    public void findByCodeBad_whenSendTaskCodeNotFound() {
        when(taskRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        var response = service.findByCode(UUID.randomUUID().toString());
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }

    @Test
    public void findByUserBad_whenSendUserCodeNotFound() {
        when(userService.findByCode(anyString()))
                .thenReturn(Response.<UserResponse>builder().success(false).message("User Not Found").build());

        var response = service.findAllByUserCode(UUID.randomUUID().toString());
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }

    @Test
    public void deleteByCodeBad_whenSendTaskCodeNotFound() {
        when(taskRepository.findById(anyString()))
                .thenReturn(Optional.empty());

        var response = service.deleteByCode(UUID.randomUUID().toString());
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }

}
