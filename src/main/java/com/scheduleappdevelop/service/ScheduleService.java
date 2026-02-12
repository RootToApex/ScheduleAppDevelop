package com.scheduleappdevelop.service;

import com.scheduleappdevelop.dto.*;
import com.scheduleappdevelop.entity.Schedule;
import com.scheduleappdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public GetScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTask(), request.getAuthor(), request.getPassword());
        Schedule saved = scheduleRepository.save(schedule);
        return new GetScheduleResponse(saved.getId(), saved.getTask(), saved.getAuthor());
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAll() {
        return scheduleRepository.findAll().stream()
                .map(s -> new GetScheduleResponse(s.getId(), s.getTask(), s.getAuthor()))
                .toList();
    }
    @Transactional(readOnly = true)
    public GetScheduleResponse findById(Long id) {
        // id로 일정 찾고 없으면 에러 띄우기
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        // 찾은 엔티티를 응답 dto로 변환해서 반환
        return new GetScheduleResponse(schedule.getId(), schedule.getTask(), schedule.getAuthor());
    }
}
