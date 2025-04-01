package com.thezz9.schedulerjpa.api.schedule.repository;

import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
