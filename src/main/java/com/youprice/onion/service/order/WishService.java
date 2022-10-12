package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.WishProductDTO;

import java.util.List;

public interface WishService {

	List<WishProductDTO> getWishList(Long memberId);

	void addWish(Long memberId, Long productId);

	void removeWish(Long wishId);

}
