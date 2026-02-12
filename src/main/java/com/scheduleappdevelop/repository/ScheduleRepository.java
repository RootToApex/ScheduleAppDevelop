package com.scheduleappdevelop.repository;

import com.scheduleappdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}