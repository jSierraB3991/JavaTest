package com.bitbucket.test.infrastructure.infrastructure;

import com.bitbucket.test.domain.service.TaskService;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.TaskRequest;
import com.bitbucket.test.infrastructure.dto.TaskResponse;
import com.bitbucket.test.infrastructure.dto.TaskUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/user/{user-code}")
    public ResponseEntity<Response<List<TaskResponse>>> findAllByUser(@PathVariable("user-code") String userCode) {
        var tasksResponse = service.findAllByUserCode(userCode);
        if (tasksResponse.isSuccess()) {
            return ResponseEntity.ok(tasksResponse);
        }
        return ResponseEntity.badRequest().body(tasksResponse);
    }


    @GetMapping("/{code}")
    public ResponseEntity<Response<TaskResponse>> findByCode(@PathVariable String code) {
        Response<TaskResponse> response = service.findByCode(code);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<TaskResponse>> save(@RequestBody TaskRequest request) {
        Response<TaskResponse> response = service.save(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Response<TaskResponse>> update(@PathVariable("code") String code,
                                                       @RequestBody TaskUpdateRequest request) {
        Response<TaskResponse> response = service.update(request, code);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Response<TaskResponse>> delete(@PathVariable String code) {
        Response<TaskResponse> response = service.deleteByCode(code);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
