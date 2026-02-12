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
}
