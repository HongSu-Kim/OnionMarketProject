package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateDTO {

    private Long townId; //동네번호

    private Long categoryId; //카테고리번호

    private String subject; //제목
    private String content; //내용
    private int price; //상품가격

    private String representativeImage; //대표이미지
    private List<MultipartFile> productImageName;//이미지 이름
    private Boolean payStatus; //페이현황

    private LocalDateTime auctionDeadline; //경매기한
    private Boolean blindStatus; //블라인드현황
    private Boolean auctionStatus; //경매현황


}
