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

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    /** 일정 생성 */
    @Override
    public ScheduleResponseDto createSchedule(ScheduleCreateRequestDto dto, Long userId) {

        // 로그인한 유저 정보
        User user = userRepository.findUserByIdOrElseThrow(userId);
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        return new ScheduleResponseDto(scheduleRepository.save(new Schedule(dto.getTitle(), dto.getContent(), encodedPassword, user)));
    }

    /** 일정 전체 조회 */
    @Override
    public Page<ScheduleResponseDto> findAllSchedules(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        return schedules.map(schedule ->
                new ScheduleResponseDto(schedule, commentRepository.countCommentsByScheduleId(schedule.getId())));
    }

    /** 일정 단건 조회 */
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule);
    }

    /** 일정 수정 */
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 비밀번호 검증
        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        schedule.updateSchedule(dto.getTitle(), dto.getContent());
        return new ScheduleResponseDto(schedule);
    }

    /** 일정 삭제 */
    @Transactional
    @Override
    public void deleteSchedule(Long id, ScheduleDeleteRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // 비밀번호 검증
        if (passwordEncoder.matches(dto.getPassword(), schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 외래키 제약조건 댓글 먼저 삭제
        commentRepository.deleteBySchedule(schedule);
        scheduleRepository.delete(schedule);
    }

}
