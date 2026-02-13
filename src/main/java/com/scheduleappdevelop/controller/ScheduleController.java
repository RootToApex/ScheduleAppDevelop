package com.scheduleappdevelop.controller;

import com.scheduleappdevelop.dto.*;
import com.scheduleappdevelop.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<GetScheduleResponse> create(
            @RequestBody CreateScheduleRequest request,
            HttpServletRequest httpRequest
    ) {
        // 세션 확인
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 세션에서 로그인된 유저 ID 추출
        Long userId = (Long) session.getAttribute("loginUser");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(scheduleService.save(request.getTask(), userId, request.getPassword()));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> getSchedule(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.findById(id));
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request,
            HttpServletRequest httpRequest
    ) {
        // 세션 확인
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(scheduleService.update(id, request));
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody DeleteScheduleRequest request,
            HttpServletRequest httpRequest
    ) {
        // 세션 확인
        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        scheduleService.delete(id, request.getPassword());
        return ResponseEntity.noContent().build();
    }
}