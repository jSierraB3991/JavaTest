package com.bitbucket.test.infrastructure.dto;

import com.bitbucket.test.domain.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest extends TaskRequest {
    private TaskState state;
}
