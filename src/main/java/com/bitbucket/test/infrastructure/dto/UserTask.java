package com.bitbucket.test.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserTask {
    private UserResponse user;
    private TaskResponse tasks;
}
