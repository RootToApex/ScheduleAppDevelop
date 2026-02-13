package com.scheduleappdevelop.dto;

import lombok.Getter;
import com.scheduleappdevelop.entity.Schedule;
import java.time.LocalDateTime;

@Getter
public class GetScheduleResponse {

    private final Long id;
    private final String task;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
