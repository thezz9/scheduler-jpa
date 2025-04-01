package com.thezz9.schedulerjpa.api.schedule.entity;

import com.thezz9.schedulerjpa.common.config.JpaAuditingConfig;
import com.thezz9.schedulerjpa.api.schedule.dto.ScheduleUpdateRequestDto;
import com.thezz9.schedulerjpa.api.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends JpaAuditingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "longtext", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateSchedule(ScheduleUpdateRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

}
