package com.thezz9.schedulerjpa.api.user.service;

import com.thezz9.schedulerjpa.api.user.dto.UserCreateRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserDeleteRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserResponseDto;
import com.thezz9.schedulerjpa.api.user.dto.UserUpdateRequestDto;
import com.thezz9.schedulerjpa.api.user.entity.User;
import com.thezz9.schedulerjpa.api.user.repository.UserRepository;
import com.thezz9.schedulerjpa.common.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(UserCreateRequestDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        User savedUser = userRepository.save(new User(dto.getUsername(), dto.getEmail(), encodedPassword));
        return new UserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).toList();
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(Long id, UserUpdateRequestDto dto) {
        User user = userRepository.findUserByIdOrElseThrow(id);

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        user.updateUser(dto);
        return new UserResponseDto(user);
    }

    @Override
    public void deleteUser(Long id, UserDeleteRequestDto dto) {
        User user = userRepository.findUserByIdOrElseThrow(id);

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        userRepository.delete(user);
    }

}
