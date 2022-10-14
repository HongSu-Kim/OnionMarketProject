package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ReviewUpdateDTO {
    private String reviewContent;
    private Integer grade;
    private List<MultipartFile> reviewImageName;
}