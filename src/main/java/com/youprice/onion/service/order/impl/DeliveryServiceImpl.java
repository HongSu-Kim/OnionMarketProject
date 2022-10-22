package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepository deliveryRepository;

	// 배송지 수정
	@Override
	public void update(DeliveryDTO deliveryDTO) {
		deliveryRepository.findById(deliveryDTO.getOrderId()).map(delivery -> delivery.update(deliveryDTO)).orElse(null);
	}
}
