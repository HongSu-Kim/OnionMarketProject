package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.WishDTO;

import java.util.List;

public interface WishService {

	List<WishDTO> getWishList(Long memberId);

	void addWish(Long memberId, Long productId);

	void removeWish(Long wishId);

}
