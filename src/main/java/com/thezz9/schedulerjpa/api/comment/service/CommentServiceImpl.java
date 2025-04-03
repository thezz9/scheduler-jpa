package com.thezz9.schedulerjpa.api.comment.service;

import com.thezz9.schedulerjpa.api.comment.dto.CommentCreateRequestDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentResponseDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentUpdateRequestDto;
import com.thezz9.schedulerjpa.api.comment.entity.Comment;
import com.thezz9.schedulerjpa.api.comment.repository.CommentRepository;
import com.thezz9.schedulerjpa.api.schedule.entity.Schedule;
import com.thezz9.schedulerjpa.api.schedule.repository.ScheduleRepository;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public CommentResponseDto createComment(Long id, CommentCreateRequestDto dto, Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new CommentResponseDto(commentRepository.save(new Comment(dto.getContent(), schedule, user)));
    }

    @Override
    public List<CommentResponseDto> findAllCommentsByScheduleId(Long ScheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(ScheduleId);
        return commentRepository.findAllCommentsByScheduleId(schedule.getId())
                .stream().map(CommentResponseDto::new).toList();
    }

    @Override
    public CommentResponseDto findCommentById(Long id) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        return new CommentResponseDto(comment);
    }

    @Transactional
    @Override
    public CommentResponseDto updateComment(Long id, CommentUpdateRequestDto dto, Long userId) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 수정할 수 있습니다.");
        }

        comment.updateComment(dto.getContent());
        return new CommentResponseDto(comment);
    }

    @Override
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

}
