package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ReviewUpdateDTO {
    private Long reviewId;
    @NotEmpty(message = "내용을 입력해주세요")
    private String reviewContent;
    @Min(value = 1, message = "별점을 평가해주세요!")
    private int grade;
    private List<MultipartFile> reviewImageName;
}