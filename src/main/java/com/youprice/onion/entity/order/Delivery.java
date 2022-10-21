package com.youprice.onion.entity.order;

import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.entity.member.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

    @Id
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;//주문번호 PK FK

	private String recipient;//받는사람
	private String deliveryTel;//연락처
    @Embedded
    private Address address;//배송지

    private String request;//요청사항
    private int deliveryCost;//배송비

    public Delivery(Order order, OrderAddDTO orderAddDTO) {
        this.order = order;
		this.recipient = orderAddDTO.getRecipient();
		this.deliveryTel = orderAddDTO.getDeliveryTel();
        this.address = new Address(orderAddDTO.getPostcode(), orderAddDTO.getAddress(),
				orderAddDTO.getDetailAddress(), orderAddDTO.getExtraAddress());
        this.request = orderAddDTO.getRequest();
        this.deliveryCost = orderAddDTO.getDeliveryCost();
    }

	public void update(DeliveryDTO deliveryDTO) {
		recipient = deliveryDTO.getRecipient();
		deliveryTel = deliveryDTO.getDeliveryTel();
		address = new Address(deliveryDTO.getPostcode(), deliveryDTO.getAddress(), deliveryDTO.getDetailAddress(), deliveryDTO.getExtraAddress());
		request = deliveryDTO.getRequest();
	}
}
