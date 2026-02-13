package com.scheduleappdevelop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateScheduleRequest {

    private String task;
    private String author;
    private String password;
}
