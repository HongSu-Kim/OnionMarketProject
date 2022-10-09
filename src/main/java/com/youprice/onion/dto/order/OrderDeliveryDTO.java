package com.youprice.onion.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDeliveryDTO {

	// order
    private Long orderId;//주문번호 PK
    private Long memberId;//회원번호 FK
    private Long productId;//상품번호 FK
	private String orderNum;//주문번호
	private String imp_uid;//결제번호
	private int orderPayment;//결제금액
	private String orderState;//주문상태-order,delivery,cancel,complete
    private LocalDateTime orderDate;//주문시간
    private LocalDateTime modifiedDate;//수정시간
    private Long reviewId;//리뷰번호

	// delivery
	private String postcode;//우편번호
	private String address;//주소
	private String detailAddress;//상세주소
	private String extraAddress;//참고사항
	private String request;//요청사항
	private int deliveryCost;//배송비

}
