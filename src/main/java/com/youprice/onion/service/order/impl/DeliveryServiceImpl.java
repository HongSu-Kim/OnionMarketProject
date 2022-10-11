package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.entity.order.Delivery;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepository deliveryRepository;

	@Override
	@Transactional(readOnly = true)
	public DeliveryDTO getDeliveryDTO(Long orderId) {
		Optional<Delivery> optional = deliveryRepository.findById(orderId);
		return optional.isPresent() ?  new DeliveryDTO(optional.get()) : null;
	}
}
