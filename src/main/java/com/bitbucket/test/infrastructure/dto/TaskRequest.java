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
public class TaskRequest {
    private String name;
    private Boolean active;
    private String userCode;
}
