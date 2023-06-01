package com.bitbucket.test.infrastructure.service.impl;

import com.bitbucket.test.domain.mapper.UserMapper;
import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.domain.service.UserService;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;
import com.bitbucket.test.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public List<UserResponse> findAll() {
        return mapper.toResponse(repository.findAll());
    }

    @Override
    public Response<UserResponse> delete(String code) {
        Response<UserResponse> response = findByCode(code);
        if(!response.isSuccess()){
            return response;
        }
        try {
            repository.deleteById(code);
            return Response.<UserResponse>builder()
                    .success(true)
                    .build();

        }catch (Exception ex){
            return Response.<UserResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Response<UserResponse> findByCode(String code) {
        Optional<User> oUser = repository.findById(code);
        if (oUser.isPresent()) {
            return Response.<UserResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(oUser.get()))
                    .build();
        }
        return Response.<UserResponse>builder()
                .success(false)
                .message("User Not Found")
                .build();
    }

    @Override
    public Response<UserResponse> save(UserRequest request) {
        try {
            User user = repository.save(mapper.toEntity(request, UUID.randomUUID().toString()));
            return Response.<UserResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(user))
                    .build();

        }catch (Exception ex){
            return Response.<UserResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }

    @Override
    public Response<UserResponse> update(UserRequest request, String code) {
        Response<UserResponse> response = findByCode(code);
        if(!response.isSuccess()){
            return response;
        }
        try {
            User user = repository.save(mapper.toEntity(request, code));
            return Response.<UserResponse>builder()
                    .success(true)
                    .response(mapper.toResponse(user))
                    .build();

        }catch (Exception ex){
            return Response.<UserResponse>builder()
                    .success(false)
                    .message(ex.getMessage())
                    .build();
        }
    }
}
