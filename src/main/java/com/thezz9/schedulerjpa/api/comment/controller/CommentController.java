package com.thezz9.schedulerjpa.api.comment.controller;

import com.thezz9.schedulerjpa.api.comment.dto.CommentCreateRequestDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentResponseDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentUpdateRequestDto;
import com.thezz9.schedulerjpa.api.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /** 댓글 생성 */
    @PostMapping("/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentCreateRequestDto dto,
                                                            HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        return new ResponseEntity<>(commentService.createComment(id, dto, userId), HttpStatus.CREATED);
    }

    /** 특정 스케줄 댓글 전체 조회 */
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllCommentsByScheduleId(@RequestParam Long ScheduleId) {
        return new ResponseEntity<>(commentService.findAllCommentsByScheduleId(ScheduleId), HttpStatus.OK);
    }

    /** 댓글 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findCommentById(id), HttpStatus.OK);
    }

    /** 댓글 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentUpdateRequestDto dto,
                                                            HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        return new ResponseEntity<>(commentService.updateComment(id, dto, userId), HttpStatus.OK);
    }

    /** 댓글 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id, HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getSession().getAttribute("userId");
        commentService.deleteComment(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
