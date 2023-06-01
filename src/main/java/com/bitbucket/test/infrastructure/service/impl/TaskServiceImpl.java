package com.bitbucket.test.infrastructure.service.impl;

import com.bitbucket.test.domain.mapper.TaskMapper;
import com.bitbucket.test.domain.model.Task;
import com.bitbucket.test.domain.model.TaskState;
import com.bitbucket.test.domain.service.TaskService;
import com.bitbucket.test.domain.service.UserService;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;
import com.bitbucket.test.infrastructure.dto.UserTask;
import com.bitbucket.test.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final UserService userService;

    @Override
    public List<TaskResponse> findAll() {
        return mapper.toResponse(repository.findAll());
    }

    @Override
    public Response<TaskResponse> findByCode(String code) {
        Optional<Task> oTask = repository.findById(code);
        if(oTask.isPresent()) {
            return Response.<TaskResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(oTask.get()))
                    .build();
        }
        return Response.<TaskResponse>builder()
                .success(false)
                .message("Task Not Found")
                .build();
    }

    @Override
    public Response<TaskResponse> save(TaskRequest request) {
        try {
            Task task = repository.save(mapper.toEntity(request, UUID.randomUUID().toString(), TaskState.CREATED));
            return Response.<TaskResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(task))
                    .build();
        }catch (Exception ex) {
            return Response.<TaskResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Response<TaskResponse> update(TaskUpdateRequest request, String code) {
        Response<TaskResponse> response = findByCode(code);
        if(!response.isSuccess()) {
            return response;
        }
        try {
            Task task = repository.save(mapper.toEntity(request, code));
            return Response.<TaskResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(task))
                    .build();
        }catch (Exception ex) {
            return Response.<TaskResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Response<TaskResponse> deleteByCode(String code) {
        Response<TaskResponse> response = findByCode(code);
        if(!response.isSuccess()) {
            return response;
        }
        try {
            repository.deleteById(code);
            return Response.<TaskResponse>builder()
                    .success(true)
                    .build();
        }catch (Exception ex) {
            return Response.<TaskResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Response<List<TaskResponse>> findAllByUserCode(String userCode) {
        var userResponse = userService.findByCode(userCode);
        if(!userResponse.isSuccess()){
            return Response.<List<TaskResponse>>builder()
                    .success(false)
                    .message(userResponse.getMessage())
                    .build();
        }
        return Response.<List<TaskResponse>>builder()
                .success(true)
                .response(mapper.toResponse(repository.findAllByUser(userCode)))
                .build();
    }
}
