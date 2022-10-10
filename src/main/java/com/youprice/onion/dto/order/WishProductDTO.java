package com.youprice.onion.dto.order;

import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class WishProductDTO {

	private Long wishId;//찜번호 Pk
	// product
	private Long productId;//상품번호 PK
//	private Long memberId;//회원번호 FK
	private String productName;//상품명
	private String subject;//제목
	private String content;//내용
	private int price;//상품가격
	private LocalDateTime uploadDate;//등록일
	private LocalDateTime updateDate;//수정일
	private int viewCount;//조회수
	private ProductProgress productProgress;//판매상태 Reserved,tradings,soldout 예약중,거래중,판매완료
	private String payStatus;//페이현황
	private String blindStatus;//블라인드현황

	public WishProductDTO(Wish wish) {
		wishId = wish.getId();
		productId = wish.getProduct().getId();
//		memberId  = wish.getProduct().getMember().getId();
		productName = wish.getProduct().getProductName();
		subject = wish.getProduct().getSubject();
		content = wish.getProduct().getContent();
		price = wish.getProduct().getPrice();
		uploadDate = wish.getProduct().getUploadDate();
		updateDate = wish.getProduct().getUpdateDate();
		viewCount = wish.getProduct().getViewCount();
		productProgress = wish.getProduct().getProductProgress();
		payStatus = wish.getProduct().getPayStatus();
		blindStatus = wish.getProduct().getBlindStatus();
	}
}
