package com.bitbucket.test.domain.mapper;

import com.bitbucket.test.domain.model.Task;
import com.bitbucket.test.domain.model.TaskState;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper {
    TaskResponse toResponse(Task task);

    @Mappings({
            @Mapping(source = "request.userCode", target = "user.code")
    })
    Task toEntity(TaskRequest request);

    @Mappings({
            @Mapping(source = "codeParam", target = "code"),
            @Mapping(source = "stateParam", target = "state"),
            @Mapping(source = "request.userCode", target = "user.code")
    })
    Task toEntity(TaskRequest request, String codeParam, TaskState stateParam);

    @Mappings({
            @Mapping(source = "request.userCode", target = "user.code")
    })
    Task toEntity(TaskUpdateRequest request);

    @Mappings({
            @Mapping(source = "codeParam", target = "code"),
            @Mapping(source = "request.userCode", target = "user.code")
    })
    Task toEntity(TaskUpdateRequest request, String codeParam);

    List<TaskResponse> toResponse(List<Task> tasks);
    List<Task> toEntity(List<TaskResponse> tasks);
}
