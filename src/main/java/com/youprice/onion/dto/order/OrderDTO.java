package com.youprice.onion.dto.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private Long orderId;//주문번호 PK
    private Long memberId;//회원번호 FK
    private Long productId;//상품번호 FK
	private String orderNum;//주문번호
	private String imp_uid;//결제번호
	private int orderPayment;//결제금액
	private OrderState orderState;//주문상태-order,delivery,cancel,complete
    private LocalDateTime orderDate;//주문시간
    private LocalDateTime modifiedDate;//수정시간

	private MemberDTO memberDTO;
	private ProductDTO productDTO;
	private DeliveryDTO deliveryDTO;

	public OrderDTO(Order order) {

		// order
		orderId = order.getId();
		memberId = order.getMember().getId();
		productId = order.getProduct().getId();
		orderNum = order.getOrderNum();
		imp_uid = order.getImp_uid();
		orderPayment = order.getOrderPayment();
		orderState = order.getOrderState();
		orderDate = order.getOrderDate();
		modifiedDate = order.getModifiedDate();

		memberDTO = new MemberDTO(order.getMember());
		productDTO = new ProductDTO(order.getProduct());
		if (order.getDelivery() != null) {
			deliveryDTO = new DeliveryDTO(order.getDelivery());
		}
	}

}
