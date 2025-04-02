package com.thezz9.schedulerjpa.api.login.service;

import com.thezz9.schedulerjpa.api.login.dto.LoginRequestDto;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.api.user.repository.UserRepository;
import com.thezz9.schedulerjpa.common.config.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User login(LoginRequestDto dto) {

        User user = userRepository.findUserByEmailOrElseThrow(dto.getEmail());

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}
