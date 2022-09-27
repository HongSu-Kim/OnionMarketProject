package com.youprice.onion.service.order.impl;

import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

}
