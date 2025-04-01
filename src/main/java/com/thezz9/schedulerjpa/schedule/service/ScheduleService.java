package com.thezz9.schedulerjpa.schedule.service;

import com.thezz9.schedulerjpa.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleUpdateRequestDto;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto);

    List<ScheduleResponseDto> findAllSchedules();

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);

    void deleteSchedule(Long id);

}
