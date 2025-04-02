package com.thezz9.schedulerjpa.api.comment.service;

import com.thezz9.schedulerjpa.api.comment.dto.CommentCreateRequestDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentResponseDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentUpdateRequestDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(Long id, CommentCreateRequestDto dto, String email);

    List<CommentResponseDto> findAllCommentsByScheduleId(Long ScheduleId);

    CommentResponseDto findCommentById(Long id);

    CommentResponseDto updateComment(Long id, CommentUpdateRequestDto dto, String email);

    void deleteComment(Long id, String email);

}
