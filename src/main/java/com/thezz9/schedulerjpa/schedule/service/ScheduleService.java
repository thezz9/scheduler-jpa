package com.thezz9.schedulerjpa.schedule.service;

import com.thezz9.schedulerjpa.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto createSchedule(String username, String title, String content);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, String title, String content);

    void deleteSchedule(Long id);
}
