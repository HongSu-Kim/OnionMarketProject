package com.youprice.onion.service.order.impl;

import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

	private final DeliveryRepository deliveryRepository;

}
