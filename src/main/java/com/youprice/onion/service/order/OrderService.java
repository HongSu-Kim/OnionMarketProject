package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface OrderService {

	OrderDTO getOrderDTO(Long orderId);
	Long addOrder(OrderAddDTO orderAddDTO) throws IOException;
    String getOrderNum();
	Page<OrderDTO> getBuyList(Long memberId, Pageable pageable);
	void cancel(Long orderId);
	void orderComplete(Long productId, Long memberId);
}
