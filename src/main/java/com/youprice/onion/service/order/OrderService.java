package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {

	OrderDTO getOrderDTO(Long orderId);

	Long addOrder(OrderAddDTO orderAddDTO);

    String getOrderNum();

	List<OrderDTO> getBuyList(Long memberId);

	List<OrderDTO> getSellList(Long memberId);

}
