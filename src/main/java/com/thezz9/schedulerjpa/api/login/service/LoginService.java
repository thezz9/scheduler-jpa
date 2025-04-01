package com.thezz9.schedulerjpa.api.login.service;

import com.thezz9.schedulerjpa.api.login.dto.LoginRequestDto;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public User login(LoginRequestDto dto) {

        User user = userRepository.findUserByEmailOrElseThrow(dto.getEmail());

        if (!dto.getPassword().equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}
