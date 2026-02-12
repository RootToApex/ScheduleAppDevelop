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

    // 단건 조회
    @Transactional(readOnly = true)
    public GetScheduleResponse findById(Long id) {
        // id로 일정 찾고 없으면 에러 띄우기
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        // 찾은 엔티티를 응답 dto로 변환해서 반환
        return new GetScheduleResponse(schedule.getId(), schedule.getTask(), schedule.getAuthor());
    }

    // 비민번호 일치 확인
    @Transactional
    public GetScheduleResponse update(Long id, UpdateScheduleRequest request) {
        // 수정할 일정 찾기
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다"));
        // 비밀번호 체크
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //데이터 수정
        schedule.update(request.getTask(), request.getAuthor());

        // 수정 결과 응답 dto로 변환해서 반환
        return new GetScheduleResponse(schedule.getId(), schedule.getTask(), schedule.getAuthor());
    }

    @Transactional
    public void delete(Long id, String password) {
        // 삭제할 일정 찾기
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + id));

        // 비밀번호 체크
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 데이터 삭제
        scheduleRepository.delete(schedule);
    }
}
