package com.thezz9.schedulerjpa.api.login.controller;

import com.thezz9.schedulerjpa.api.login.dto.LoginRequestDto;
import com.thezz9.schedulerjpa.api.login.service.LoginService;
import com.thezz9.schedulerjpa.api.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto, HttpServletRequest httpRequest) {

        User user = loginService.login(dto);

        HttpSession session = httpRequest.getSession();
        session.setAttribute("userEmail", user.getEmail());

        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃", HttpStatus.OK);
    }
}
