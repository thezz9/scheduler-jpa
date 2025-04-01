package com.thezz9.schedulerjpa.schedule.repository;

import com.thezz9.schedulerjpa.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
