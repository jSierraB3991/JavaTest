package com.bitbucket.test.infrastructure.infrastructure;

import com.bitbucket.test.domain.service.UserService;
import com.bitbucket.test.infrastructure.dto.Response;
import com.bitbucket.test.infrastructure.dto.UserRequest;
import com.bitbucket.test.infrastructure.dto.UserResponse;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Response<UserResponse>> findByCode(@PathVariable("code") String code) {
        Response<UserResponse> response = service.findByCode(code);
        if (response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<UserResponse>> save(@RequestBody UserRequest request) {
        Response<UserResponse> response = service.save(request);
        if (response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Response<UserResponse>> update(@PathVariable("code") String code,
                                                         @RequestBody UserRequest request) {
        Response<UserResponse> response = service.update(request, code);
        if (response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Response<UserResponse>> delete(@PathVariable("code") String code) {
        Response<UserResponse> response = service.delete(code);
        if (response.isSuccess()){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body(response);
    }
}
