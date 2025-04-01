package com.thezz9.schedulerjpa.schedule.service;

import com.thezz9.schedulerjpa.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.schedule.dto.ScheduleUpdateRequestDto;
import com.thezz9.schedulerjpa.schedule.entity.Schedule;
import com.thezz9.schedulerjpa.schedule.repository.ScheduleRepository;
import com.thezz9.schedulerjpa.user.entity.User;
import com.thezz9.schedulerjpa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto) {
        User user = userRepository.findUserByUsernameOrElseThrow(dto.getUsername());
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContent(), user)));
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::new).toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 일정이 존재하지 않습니다."));
        schedule.updateSchedule(dto);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 일정이 존재하지 않습니다."));
        scheduleRepository.delete(schedule);
    }

}
