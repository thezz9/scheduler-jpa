package com.thezz9.schedulerjpa.api.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자 이내로 입력하세요.")
    private final String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 20, message = "내용은 50자 이내로 입력하세요.")
    private final String content;

}
