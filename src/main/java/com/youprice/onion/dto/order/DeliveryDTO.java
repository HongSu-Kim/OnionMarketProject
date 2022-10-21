package com.youprice.onion.dto.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.order.Delivery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryDTO {

	private Long orderId;//주문번호 PK FK
	private String recipient;//받는사람
	private String deliveryTel;//연락처
	private String postcode;//우편번호
	private String address;//주소
	private String detailAddress;//상세주소
	private String extraAddress;//참고사항
	private String request;//요청사항
	private int deliveryCost;//배송비

	public DeliveryDTO(Delivery delivery) {
		orderId = delivery.getId();

		postcode = delivery.getAddress().getPostcode();
		address = delivery.getAddress().getAddress();
		detailAddress = delivery.getAddress().getDetailAddress();
		extraAddress = delivery.getAddress().getExtraAddress();
		request = delivery.getRequest();
		deliveryCost = delivery.getDeliveryCost();
	}

}
