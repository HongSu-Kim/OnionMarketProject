package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ReviewFormDTO {

    private Long orderId;
    private Long memberId;
    @NotEmpty(message = "내용을 입력해주세요")
    private String reviewContent;
    @Min(value = 1, message = "별점을 평가해주세요!")
    private int grade;
    private Long salesId;
}
