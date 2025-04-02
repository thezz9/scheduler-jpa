package com.thezz9.schedulerjpa.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDeleteRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(max = 20, message = "비밀번호는 20자 이내로 입력하세요.")
    private final String password;

}
