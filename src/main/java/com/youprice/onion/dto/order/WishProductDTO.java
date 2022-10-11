package com.youprice.onion.dto.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.order.Wish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishProductDTO {

	private Long wishId;//찜번호 Pk
	private MemberDTO memberDTO;
	private ProductDTO productDTO;

	public WishProductDTO(Wish wish) {
		wishId = wish.getId();
//		memberDTO = new MemberDTO(wish.getMember());
		productDTO = new ProductDTO(wish.getProduct());

	}
}
