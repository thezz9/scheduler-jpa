package com.thezz9.schedulerjpa.api.comment.repository;

import com.thezz9.schedulerjpa.api.comment.entity.Comment;
import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    /** 댓글 단건 조회 메서드 */
    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 댓글이 존재하지 않습니다."));
    }

    /** 특정 스케줄 댓글 전체 조회 메서드 */
    List<Comment> findAllCommentsByScheduleId(Long id);

    /** 특정 스케줄 댓글 갯수 조회 메서드 */
    @Query("select count(c) from Comment c where c.schedule.id = :scheduleId")
    int countCommentsByScheduleId(@Param("scheduleId") Long scheduleId);

    /** 특정 스케줄 댓글 삭제 메서드 */
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.schedule = :schedule")
    void deleteBySchedule(@Param("schedule") Schedule schedule);

}
