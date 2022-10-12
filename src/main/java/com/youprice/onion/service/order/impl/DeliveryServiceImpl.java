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

	@Override
	@Transactional(readOnly = true)
	public DeliveryDTO getDeliveryDTO(Long orderId) {
		return deliveryRepository.findById(orderId).map(DeliveryDTO::new).orElse(null);
	}
}
