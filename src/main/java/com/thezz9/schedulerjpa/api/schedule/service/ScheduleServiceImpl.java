package com.thezz9.schedulerjpa.api.schedule.service;

import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleCreateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleDeleteRequestDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleUpdateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import com.thezz9.schedulerjpa.api.schedule.repository.ScheduleRepository;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.api.user.repository.UserRepository;
import com.thezz9.schedulerjpa.common.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto, String email) {
        User user = userRepository.findUserByEmailOrElseThrow(email);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContent(), encodedPassword, user)));
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

        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(dto);
        return new ScheduleResponseDto(schedule);
    }

    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 일정이 존재하지 않습니다."));

        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }

}
