package com.thezz9.schedulerjpa.api.schedule.service;

import com.thezz9.schedulerjpa.api.comment.repository.CommentRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto, Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContent(), encodedPassword, user)));
    }

    @Override
    public Page<ScheduleResponseDto> findAllSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(schedule ->
                new ScheduleResponseDto(schedule, commentRepository.countCommentsByScheduleId(schedule.getId())));
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(dto.getTitle(), dto.getContent());
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        commentRepository.deleteBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }

}
