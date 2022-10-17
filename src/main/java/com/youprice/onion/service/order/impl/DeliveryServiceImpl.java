package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.member.MemberDTO;
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

//		return deliveryRepository.findById(orderId).map(delivery -> {
//					DeliveryDTO deliveryDTO = new DeliveryDTO(delivery);
//					deliveryDTO.getOrderDTO().setMemberDTO(new MemberDTO(delivery.getOrder().getMember()));
//					return deliveryDTO;
//				}).orElse(null);

		return deliveryRepository.findById(orderId).map(DeliveryDTO::new).orElse(null);
	}
}
