package com.scheduleappdevelop.dto;

import lombok.Getter;

@Getter
public class GetScheduleResponse {

    private final Long id;
    private final String task;
    private final String author;

    public GetScheduleResponse(Long id, String task, String author) {
        this.id = id;
        this.task = task;
        this.author = author;
    }
}
