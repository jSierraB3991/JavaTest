package com.bitbucket.test.domain.mapper;

import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapperMock implements UserMapper {
    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .code(user.getCode())
                .lastName(user.getLastName()).build();
    }

    @Override
    public User toEntity(UserRequest user) {
        return User.builder().name(user.getName()).build();
    }

    @Override
    public User toEntity(UserResponse user) {
        return User.builder().name(user.getName()).lastName(user.getLastName()).build();
    }

    @Override
    public User toEntity(UserRequest user, String codeParam) {
        return User.builder().name(user.getName()).lastName(user.getLastName()).code(codeParam).build();
    }

    @Override
    public List<UserResponse> toResponse(List<User> users) {
        return users.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<User> toEntity(List<UserResponse> users) {
        return users.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
