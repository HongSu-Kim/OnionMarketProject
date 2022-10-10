package com.youprice.onion.dto.order;

import com.youprice.onion.entity.order.Delivery;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDTO {

	private Long orderId;//주문번호 PK FK
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
