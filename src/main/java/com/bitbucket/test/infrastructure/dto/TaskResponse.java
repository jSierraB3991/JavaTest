package com.bitbucket.test.infrastructure.dto;

import com.bitbucket.test.domain.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    private String code;
    private String name;
    private Boolean active;
    private TaskState state;
    private UserResponse user;
}
