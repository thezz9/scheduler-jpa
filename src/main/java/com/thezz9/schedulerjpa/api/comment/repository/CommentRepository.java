package com.thezz9.schedulerjpa.api.comment.repository;

import com.thezz9.schedulerjpa.api.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    default Comment findCommentByIdOrElseThrow(Long id) {
        return findCommentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 댓글이 존재하지 않습니다."));
    }

    List<Comment> findAllCommentsByScheduleId(Long id);

}
