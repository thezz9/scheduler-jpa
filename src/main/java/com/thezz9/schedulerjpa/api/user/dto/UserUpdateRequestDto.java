package com.thezz9.schedulerjpa.api.user.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateRequestDto {

    @Size(max = 5, message = "이름은 5자 이내로 입력하세요.")
    private final String username;

}
