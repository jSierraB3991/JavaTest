package com.bitbucket.test.domain.mapper;

import com.bitbucket.test.domain.model.Task;
import com.bitbucket.test.domain.model.TaskState;
import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;

import java.util.List;

public class TaskMapperMock implements TaskMapper {
    @Override
    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .active(task.getActive())
                .name(task.getName())
                .state(task.getState())
                .code(task.getCode())
                .user(UserResponse.builder().code(task.getUser().getCode()).build()).build();
    }

    @Override
    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .active(request.getActive())
                .name(request.getName())
                .user(User.builder().code(request.getUserCode()).build()).build();
    }

    @Override
    public Task toEntity(TaskRequest request, String codeParam, TaskState stateParam) {
        return Task.builder()
                .active(request.getActive())
                .name(request.getName())
                .state(stateParam)
                .code(codeParam)
                .user(User.builder().code(request.getUserCode()).build()).build();
    }

    @Override
    public Task toEntity(TaskUpdateRequest request) {
        return null;
    }

    @Override
    public Task toEntity(TaskUpdateRequest request, String codeParam) {
        return null;
    }

    @Override
    public List<TaskResponse> toResponse(List<Task> tasks) {
        return null;
    }

    @Override
    public List<Task> toEntity(List<TaskResponse> tasks) {
        return null;
    }
}
