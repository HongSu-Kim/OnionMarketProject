package com.youprice.onion.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class OrderAddDTO {

	private Long memberId;//회원번호
	private Long productId;//상품번호

    private int orderPayment;//주문가격
	private int usePoint;//사용포인트
    private String orderNum;//주문번호
    private String imp_uid;//결제번호

	// delivery
	private boolean delivery;
	private String recipient;//받는사람
	private String deliveryTel;//연락처
    // address
    private String postcode;//우편번호
    private String address;//주소
    private String detailAddress;//상세주소
    private String extraAddress;//참고사항

    private String request;//요청사항
	private int deliveryCost;//배송비

}
