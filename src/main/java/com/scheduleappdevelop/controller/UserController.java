package com.scheduleappdevelop.controller;

import com.scheduleappdevelop.dto.UserRequest;
import com.scheduleappdevelop.dto.UserResponse;
import com.scheduleappdevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    // 유저 등록 API
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = userService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 유저 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse response = userService.findById(id);
        return ResponseEntity.ok(response);
    }
}