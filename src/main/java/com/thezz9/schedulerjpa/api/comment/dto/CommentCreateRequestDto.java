package com.thezz9.schedulerjpa.api.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateRequestDto {

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 100, message = "내용은 100자 이내로 입력하세요.")
    private final String content;

}
