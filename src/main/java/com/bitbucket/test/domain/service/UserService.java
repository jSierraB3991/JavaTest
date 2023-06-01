package com.bitbucket.test.domain.service;

import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    Response<UserResponse> delete(String code);
    Response<UserResponse> findByCode(String code);
    Response<UserResponse> save(UserRequest request);
    Response<UserResponse> update(UserRequest request, String code);
}
