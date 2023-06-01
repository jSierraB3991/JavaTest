package com.bitbucket.test.domain.mapper;

import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);
    User toEntity(UserRequest user);
    User toEntity(UserResponse user);

    @Mappings({
            @Mapping(source = "codeParam", target = "code")
    })
    User toEntity(UserRequest user, String codeParam);

    List<UserResponse> toResponse(List<User> users);
    List<User> toEntity(List<UserResponse> users);
}
