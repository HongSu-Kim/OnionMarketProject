package com.youprice.onion.service.Impl;

import com.youprice.onion.repository.OrderRepository;
import com.youprice.onion.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

}
