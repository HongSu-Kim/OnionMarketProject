package com.youprice.onion.dto.order;

import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishListDTO {

	private Long wishId;
	private Long productId;
	private String memberNickname;
	private String townName;
	private String subject; //제목
	private int price; //상품가격
	private int viewCount; //조회수
	private Boolean payStatus; //페이현황
	private String representativeImage; //이미지파일이름
	@Setter
	private Long chatroomId;
	private int chatroomListSize;
	private int wishListSize;

	public WishListDTO(Wish wish, int chatroomListSize, int wishListSize) {
		wishId = wish.getId();
		Product product = wish.getProduct();
		productId = product.getId();
		memberNickname = product.getMember().getNickname();
		townName = product.getTown().getCoordinate().getTownName();
		subject = product.getSubject();
		price = product.getPrice();
		viewCount = product.getViewCount();
		payStatus = product.getPayStatus();
		representativeImage = product.getRepresentativeImage();
		this.chatroomListSize = chatroomListSize;
		this.wishListSize = wishListSize;
	}
}


















