package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.order.OrderDeliveryDTO;
import com.youprice.onion.dto.order.OrderProductDTO;

import java.util.List;

public interface OrderService {

	OrderDTO getOrderDTO(Long orderId);

	Long addOrder(OrderAddDTO orderAddDTO);

    String getOrderNum();

	List<OrderProductDTO> getBuyList(Long memberId);

	List<OrderProductDTO> getSellList(Long memberId);

	OrderDeliveryDTO getOrderDeliveryDTO(Long orderId);

}
