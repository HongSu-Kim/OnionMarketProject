package com.youprice.onion.dto.order;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderProductDTO {

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

	// product
	private Long buyerId;//회원번호 FK
	private String productName;//상품명
	private String subject;//제목
	private int price;//상품가격
	private LocalDateTime uploadDate;//등록일
	private LocalDateTime updateDate;//수정일

	public OrderProductDTO(Order order) {
		// order
		orderId = order.getId();
		memberId = order.getMember().getId();
		productId = order.getProduct().getId();
		orderNum = order.getOrderNum();
		imp_uid = order.getImp_uid();
		orderPayment = order.getOrderPayment();
		orderState = order.getOrderState().name();
		orderDate = order.getOrderDate();
		modifiedDate = order.getModifiedDate();
		// product
		buyerId = order.getProduct().getMember().getId();
		productName = order.getProduct().getProductName();
		subject = order.getProduct().getSubject();
		price = order.getProduct().getPrice();
		uploadDate = order.getProduct().getUploadDate();
		updateDate = order.getProduct().getUpdateDate();
	}

}
