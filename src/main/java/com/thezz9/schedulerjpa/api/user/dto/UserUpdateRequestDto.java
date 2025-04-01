package com.thezz9.schedulerjpa.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDto {

    private final String username;
    private final String email;

}
