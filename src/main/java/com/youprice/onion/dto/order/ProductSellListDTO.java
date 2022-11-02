package com.youprice.onion.dto.order;

import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProductSellListDTO {

	private Long productId;
	private String subject; //제목
	private int price; //상품가격
	private LocalDateTime date; //등록시간
	private ProductProgress productProgress; //판매상태 SALESON,RESERVED,TRADINGS,SOLDOUT 판매중,예약중,거래중,판매완료
	private Boolean payStatus; //페이현황
	private Boolean blindStatus; //블라인드현황

	private String representativeImage;
	private Long orderId;
	private Long reviewId;

	public ProductSellListDTO(Product product) {

		// product
		productId = product.getId();
		subject = product.getSubject();
		date = product.getUpdateDate() != null ? product.getUpdateDate() : product.getUploadDate();
		price = product.getPrice();
		productProgress = product.getProductProgress();
		payStatus = product.getPayStatus();
		blindStatus = product.getBlindStatus();
		representativeImage = product.getRepresentativeImage();

		List<Order> orderList = product.getOrderList().stream().filter(order -> order.getOrderState() != OrderState.CANCEL).collect(Collectors.toList());
		if (orderList.size() != 0 ) {
			orderId = orderList.get(0).getId();
		}
		if (orderId != null && orderList.get(0).getReviewList().size() != 0) {
			reviewId = orderList.get(0).getReviewList().get(0).getId();
		}
	}
}
