package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewFormDTO {

    private Long orderId;
    private String reviewContent;
    private Integer grade;
}
