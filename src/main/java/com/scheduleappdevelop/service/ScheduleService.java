package com.scheduleappdevelop.service;

import com.scheduleappdevelop.dto.*;
import com.scheduleappdevelop.entity.Schedule;
import com.scheduleappdevelop.entity.User;
import com.scheduleappdevelop.repository.ScheduleRepository;
import com.scheduleappdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 일정 생성
    @Transactional
    public GetScheduleResponse save(String task, Long userId, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Schedule schedule = new Schedule(task, user, password);
        Schedule saved = scheduleRepository.save(schedule);

        return new GetScheduleResponse(saved);
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. ID: " + id));
        return new GetScheduleResponse(schedule);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream()
                .map(s -> new GetScheduleResponse(s))
                .collect(Collectors.toList());
    }

    // 일정 수정
    @Transactional
    public GetScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정할 일정이 없습니다."));

        // 비밀번호 체크
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 데이터 수정 (Dirty Checking에 의해 자동 저장)
        schedule.update(request.getTask());

        // 수정된 결과를 응답 DTO로 변환해서 반환
        return new GetScheduleResponse(schedule);
    }

    // 일정 삭제
    @Transactional
    public void delete(Long id, String password) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 체크
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 데이터 삭제
        scheduleRepository.delete(schedule);
    }
}