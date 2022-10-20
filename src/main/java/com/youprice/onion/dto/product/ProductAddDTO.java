package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddDTO {

    @NotNull(message = "동네를 선택해주세요.")
    private String townName; //동네이름

    private Long memberId; //회원번호
    private Long townId; //동네번호

    @NotNull(message = "카테고리를 선택해주세요.")
    private Long categoryId; //카테고리번호
    private Long orderId; //주문번호

    @NotBlank(message = "제목은 필수 입력입니다.")
    private String subject; //제목
    @NotBlank(message = "설명은 필수 입력입니다.")
    private String content; //설명
    @NotNull(message = "가격을 입력해주세요.")
    @Min(value = 1, message = "가격은 최소 1원 이상입니다.")
    private int price; //상품가격
    private LocalDateTime auctionDeadline; //경매기한
    private Boolean auctionStatus; //경매현황
    private Boolean payStatus; //페이현황

    @NotBlank(message = "이미지는 최소 1개 이상 등록해주시길 바랍니다.")
    private String productImageName; //이미지 이름
    private String representativeImage; //대표이미지
}
