package com.thezz9.schedulerjpa.api.schedule.service;

import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleDeleteRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

    ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto, Long userId);

    Page<ScheduleResponseDto> findAllSchedules(Pageable pageable);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);

    void deleteSchedule(Long id, ScheduleDeleteRequestDto dto);

}
