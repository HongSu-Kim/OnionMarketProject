package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.WishListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WishService {

	Page<WishListDTO> getWishList(Long memberId, Pageable pageable);
	int addWish(Long memberId, Long productId);
	int removeWish(Long memberId, Long productId);
}
