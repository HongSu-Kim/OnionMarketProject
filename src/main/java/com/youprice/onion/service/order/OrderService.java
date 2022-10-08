package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;

public interface OrderService {

	OrderDTO getOrderDTO(Long orderId);
    void addOrder(OrderAddDTO orderAddDTO);

    String getOrderNum();
}
