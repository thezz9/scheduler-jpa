package com.thezz9.schedulerjpa.schedule.controller;

import com.thezz9.schedulerjpa.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleUpdateRequestDto;
import com.thezz9.schedulerjpa.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto dto) {
        ScheduleResponseDto resDto = scheduleService.createSchedule(dto.getUsername(), dto.getTitle(), dto.getContent());
        return new ResponseEntity<>(resDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto dto) {
        ScheduleResponseDto resDto = scheduleService.updateSchedule(id, dto.getTitle(), dto.getContent());
        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
