package com.youprice.onion.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OrderAddDTO {

    private Long memberId;//회원번호 FK
    private Long productId;//상품번호 FK

    private int orderPrice;//주문가격

    // address
    @Size(min = 5, max = 5, message = "우편번호를 정확히 입력해주세요")
    private String postcode;//우편번호
    @NotEmpty(message = "주소를 입력해주세요")
    private String address;//주소
    private String detailAddress;//상세주소
    @NotEmpty(message = "참고사항을 입력해주세요")
    private String extraAddress;//참고사항

    private String request;//요청사항
    private int deliveryCost;//배송비

}
