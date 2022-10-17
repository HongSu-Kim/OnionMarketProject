package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.WishDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface WishService {

	Page<WishDTO> getWishList(Long memberId, Pageable pageable);

	void addWish(Long memberId, Long productId);

	void removeWish(Long wishId);

}
