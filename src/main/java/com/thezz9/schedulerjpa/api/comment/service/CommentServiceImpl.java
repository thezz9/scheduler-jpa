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

    /** 댓글 생성 */
    @Override
    public CommentResponseDto createComment(Long id, CommentCreateRequestDto dto, Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new CommentResponseDto(commentRepository.save(new Comment(dto.getContent(), schedule, user)));
    }

    /** 특정 스케줄 댓글 전체 조회 */
    @Override
    public List<CommentResponseDto> findAllCommentsByScheduleId(Long ScheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(ScheduleId);
        return commentRepository.findAllCommentsByScheduleId(schedule.getId())
                .stream().map(CommentResponseDto::new).toList();
    }

    /** 댓글 단건 조회 */
    @Override
    public CommentResponseDto findCommentById(Long id) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        return new CommentResponseDto(comment);
    }

    /** 댓글 수정 */
    @Transactional
    @Override
    public CommentResponseDto updateComment(Long id, CommentUpdateRequestDto dto, Long userId) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        // 로그인 유저가 댓글 작성자인지 검증
        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 수정할 수 있습니다.");
        }

        comment.updateComment(dto.getContent());
        return new CommentResponseDto(comment);
    }

    /** 댓글 삭제 */
    @Override
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findCommentByIdOrElseThrow(id);

        // 로그인 유저가 댓글 작성자인지 검증
        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);
    }

}
