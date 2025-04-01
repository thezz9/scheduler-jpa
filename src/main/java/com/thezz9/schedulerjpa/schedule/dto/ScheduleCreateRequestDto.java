package com.thezz9.schedulerjpa.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {
    private final String username;
    private final String title;
    private final String content;
}
