package com.thezz9.schedulerjpa.schedule.dto;

import com.thezz9.schedulerjpa.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getUsername(), schedule.getTitle(), schedule.getContent());
    }
}
