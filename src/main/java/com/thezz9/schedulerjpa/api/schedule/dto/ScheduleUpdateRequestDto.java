package com.thezz9.schedulerjpa.api.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

    private final String title;
    private final String content;

}
