package com.bitbucket.test.domain.service;

import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;

import java.util.List;

public interface TaskService {
    List<TaskResponse> findAll();
    Response<TaskResponse> findByCode(String code);
    Response<TaskResponse> save(TaskRequest request);
    Response<TaskResponse> update(TaskUpdateRequest request, String code);
    Response<TaskResponse> deleteByCode(String code);
    Response<List<TaskResponse>> findAllByUserCode(String userCode);
}
