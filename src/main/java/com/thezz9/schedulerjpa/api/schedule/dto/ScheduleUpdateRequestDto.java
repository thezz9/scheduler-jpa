package com.thezz9.schedulerjpa.api.schedule.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {

    @Size(max = 20, message = "제목은 20자 이내로 입력하세요.")
    private final String title;

    @Size(max = 50, message = "내용은 50자 이내로 입력하세요.")
    private final String content;

}
