package com.thezz9.schedulerjpa.api.comment.entity;

import com.thezz9.schedulerjpa.api.comment.dto.CommentUpdateRequestDto;
import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.common.config.JpaAuditingConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends JpaAuditingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "longtext", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String content, Schedule schedule, User user) {
        this.content = content;
        this.schedule = schedule;
        this.user = user;
    }

    public void updateComment(CommentUpdateRequestDto dto) {
        this.content = dto.getContent();
    }

}
