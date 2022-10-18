package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.WishListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishService {

	Page<WishListDTO> getWishList(Long memberId, Pageable pageable);
	void addWish(Long memberId, Long productId);
	void removeWish(Long wishId);
}
