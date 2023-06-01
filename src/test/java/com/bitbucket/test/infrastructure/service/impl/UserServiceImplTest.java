package com.bitbucket.test.infrastructure.service.impl;

import com.bitbucket.test.domain.mapper.UserMapper;
import com.bitbucket.test.domain.model.User;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;
import com.bitbucket.test.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class UserServiceImplTest {

    private final UserServiceImpl service;
    private final UserRepository repository;

    @Autowired
    public UserServiceImplTest(UserRepository repository) {
        service = new UserServiceImpl(repository, new UserMapperImpl());
        this.repository = repository;
    }

    private User getUser(String name, String lastName){
        return User.builder().name(name).lastName(lastName).code(UUID.randomUUID().toString()).build();
    }

    @Test
    public void findAll_success() {
        final Integer lengthData = 2;
        final String name1 = "Jon";
        final String name2 = "Davis";
        final String lastName = "Doe";
        repository.save(getUser(name1, lastName));
        repository.save(getUser(name2, lastName));

        List<UserResponse> response = service.findAll();
        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(response.size(), lengthData);
        assertThat(response.stream().map(UserResponse::getName).collect(Collectors.toList()), is(List.of(name1, name2)));
    }

    @Test
    public void saveAndFindByCode_success() {
        final String name = "Jon";
        final String lastName = "Doe";

        Response<UserResponse> response = service.save(UserRequest.builder().name(name).lastName(lastName).build());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        String code = response.getResponse().getCode();
        response = service.findByCode(code);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        Assertions.assertEquals(response.getResponse().getName(), name);
        Assertions.assertEquals(response.getResponse().getLastName(), lastName);
    }

    @Test
    public void deleteBad_whenDeleteCodeNotExits() {
        final String code = UUID.randomUUID().toString();
        Response<UserResponse> response = service.delete(code);

        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }
    @Test
    public void deleteAndFindByCode_success() {
        final String name = "Jon";
        final String lastName = "Doe";

        Response<UserResponse> response = service.save(UserRequest.builder().name(name).lastName(lastName).build());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        final String code = response.getResponse().getCode();
        response = service.delete(code);
        Assertions.assertTrue(response.isSuccess());

        response = service.findByCode(code);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());
    }

    @Test
    public void update_success() {
        final String name = "Jon";
        final String lastName = "Doe";

        Response<UserResponse> response = service.save(UserRequest.builder().name(name).lastName(lastName).build());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        final String code = response.getResponse().getCode();
        final String newName = "Peter";
        final String newLastName = "Angel";

        response = service.update(UserRequest.builder().name(newName).lastName(newLastName).build(), code);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        response = service.findByCode(code);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        Assertions.assertNotEquals(response.getResponse().getName(), name);
        Assertions.assertNotEquals(response.getResponse().getLastName(), lastName);
        Assertions.assertEquals(response.getResponse().getName(), newName);
        Assertions.assertEquals(response.getResponse().getLastName(), newLastName);
    }

    @Test
    public void findByCodeWorn_whenSendWornUserCode() {
        final String name = "Jon";
        final String lastName = "Doe";

        Response<UserResponse> response = service.save(UserRequest.builder().name(name).lastName(lastName).build());
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getResponse());

        final String code = response.getResponse().getCode();
        String newCode = "";
        do{
            newCode = UUID.randomUUID().toString();
        } while(code.equals(newCode));

        response = service.findByCode(newCode);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getResponse());
        Assertions.assertNotNull(response.getMessage());

    }

    class UserMapperImpl implements UserMapper {

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
}
