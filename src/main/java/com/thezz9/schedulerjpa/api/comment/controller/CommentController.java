package com.thezz9.schedulerjpa.api.comment.controller;

import com.thezz9.schedulerjpa.api.comment.dto.CommentCreateRequestDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentResponseDto;
import com.thezz9.schedulerjpa.api.comment.dto.CommentUpdateRequestDto;
import com.thezz9.schedulerjpa.api.comment.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentCreateRequestDto dto,
                                                            HttpServletRequest httpRequest) {
        String email = (String) httpRequest.getSession().getAttribute("userEmail");
        return new ResponseEntity<>(commentService.createComment(id, dto, email), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllCommentsByScheduleId(@RequestParam Long ScheduleId) {
        return new ResponseEntity<>(commentService.findAllCommentsByScheduleId(ScheduleId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findCommentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @Valid @RequestBody CommentUpdateRequestDto dto,
                                                            HttpServletRequest httpRequest) {
        String email = (String) httpRequest.getSession().getAttribute("userEmail");
        return new ResponseEntity<>(commentService.updateComment(id, dto, email), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id, HttpServletRequest httpRequest) {
        String email = (String) httpRequest.getSession().getAttribute("userEmail");
        commentService.deleteComment(id, email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
