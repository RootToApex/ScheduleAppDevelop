package com.scheduleappdevelop.service;

import com.scheduleappdevelop.dto.UserRequest;
import com.scheduleappdevelop.dto.UserResponse;
import com.scheduleappdevelop.entity.User;
import com.scheduleappdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 등록
    @Transactional
    public UserResponse save(UserRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다. ID: " + id));
        return new UserResponse(user);
    }

    // 유저 전체 조회 (필요하다면 추가)
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}