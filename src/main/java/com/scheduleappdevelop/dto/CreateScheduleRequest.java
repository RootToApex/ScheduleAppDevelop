package com.scheduleappdevelop.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String task;
    private String author;
    private String password;
}
