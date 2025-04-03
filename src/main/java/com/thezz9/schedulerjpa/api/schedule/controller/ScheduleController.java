package com.thezz9.schedulerjpa.api.schedule.controller;

import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleDeleteRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleUpdateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /** 일정 생성 */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleCreateRequestDto dto,
                                                              HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        return new ResponseEntity<>(scheduleService.createSchedule(dto, userId), HttpStatus.CREATED);
    }

    /** 일정 전체 조회 */
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> findAllSchedules(
            @PageableDefault(size = 5, page = 0, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(scheduleService.findAllSchedules(pageable));
    }

    /** 일정 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    /** 일정 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,
                                                              @Valid @RequestBody ScheduleUpdateRequestDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    /** 일정 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleDeleteRequestDto dto) {
        scheduleService.deleteSchedule(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
