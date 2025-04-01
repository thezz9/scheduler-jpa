package com.thezz9.schedulerjpa.api.user.service;

import com.thezz9.schedulerjpa.api.user.dto.UserCreateRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserResponseDto;
import com.thezz9.schedulerjpa.api.user.dto.UserUpdateRequestDto;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        User savedUser = userRepository.save(new User(dto.getUsername(), dto.getEmail(), dto.getPassword()));
        return new UserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 사용자가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 사용자가 존재하지 않습니다."));
        user.updateUser(dto);
        return new UserResponseDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 사용자가 존재하지 않습니다."));
        userRepository.delete(user);
    }

}
