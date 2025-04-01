package com.thezz9.schedulerjpa.api.user.service;

import com.thezz9.schedulerjpa.api.user.dto.UserCreateRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserResponseDto;
import com.thezz9.schedulerjpa.api.user.dto.UserUpdateRequestDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserCreateRequestDto dto);

    List<UserResponseDto> findAllUsers();

    UserResponseDto findUserById(Long id);

    UserResponseDto updateUser(Long id, UserUpdateRequestDto dto);

    void deleteUser(Long id);

}
