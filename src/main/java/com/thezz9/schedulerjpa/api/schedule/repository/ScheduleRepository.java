package com.thezz9.schedulerjpa.api.schedule.repository;

import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<Schedule> findScheduleById(Long id);

    /** 일정 단건 조회 메서드 */
    default Schedule findScheduleByIdOrElseThrow(Long id) {
        return findScheduleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 일정이 존재하지 않습니다."));
    }
}
