package com.thezz9.schedulerjpa.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  전역 예외 처리
     *  ResponseStatusException이 발생하면 JSON 응답을 반환함
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", ex.getStatusCode().value()); // HTTP 상태 코드
        error.put("error", HttpStatus.valueOf(ex.getStatusCode().value()).getReasonPhrase()); // 상태 코드 설명
        error.put("message", ex.getReason()); // 예외 발생 시 전달된 메시지
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

}
