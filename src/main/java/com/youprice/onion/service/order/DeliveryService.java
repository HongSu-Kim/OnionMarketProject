package com.youprice.onion.service.order;

import com.youprice.onion.dto.order.DeliveryDTO;

public interface DeliveryService {

	DeliveryDTO getDeliveryDTO(Long orderId);
	void update(DeliveryDTO deliveryDTO);
}
