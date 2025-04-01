package com.thezz9.schedulerjpa.schedule.service;

import com.thezz9.schedulerjpa.schedule.dto.ScheduleResponseDto;
import com.thezz9.schedulerjpa.schedule.entity.Schedule;
import com.thezz9.schedulerjpa.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto createSchedule(String username, String title, String content) {
        Schedule saved = scheduleRepository.save(new Schedule(username, title, content));
        return new ScheduleResponseDto(saved.getId(), saved.getUsername(), saved.getTitle(), saved.getContent());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> findSchedule = scheduleRepository.findById(id);
        return new ScheduleResponseDto(
                findSchedule.get().getId(),
                findSchedule.get().getUsername(),
                findSchedule.get().getTitle(),
                findSchedule.get().getContent());
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, String title, String content) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);
        findSchedule.updateSchedule(title, content);
        return new ScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getUsername(),
                findSchedule.getTitle(),
                findSchedule.getContent());
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
