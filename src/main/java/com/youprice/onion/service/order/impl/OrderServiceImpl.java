package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Delivery;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final DeliveryRepository deliveryRepository;
	private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public OrderDTO getOrderDTO(Long orderId) {
		return orderRepository.findById(orderId).map(OrderDTO::new).orElse(null);
	}

	// 주문 완료
	@Override
	public Long addOrder(OrderAddDTO orderAddDTO) {

		Member member = memberRepository.findById(orderAddDTO.getMemberId()).orElse(null);
		Product product = productRepository.findById(orderAddDTO.getProductId()).orElse(null);

		// 주문내역 생성
		Order order = new Order(member, orderAddDTO.getOrderNum(), orderAddDTO.getImp_uid(), orderAddDTO.getOrderPayment());
		Long orderId = orderRepository.save(order).getId();

		// 배송정보 생성
		Delivery delivery = new Delivery(order, orderAddDTO.getPostcode(), orderAddDTO.getAddress(), orderAddDTO.getDetailAddress(),
				orderAddDTO.getExtraAddress(), orderAddDTO.getRequest(), orderAddDTO.getDeliveryCost());
		deliveryRepository.save(delivery);

		product.order(order);
		productRepository.save(product);

		return orderId;
	}

	// orderNum 생성
	@Override
	@Transactional(readOnly = true)
	public String getOrderNum() {

		LocalDateTime now = LocalDateTime.now();
		String orderNum;

		do {
			orderNum = now.format(DateTimeFormatter.BASIC_ISO_DATE).substring(2)
					+ now.format(DateTimeFormatter.ISO_LOCAL_TIME).replaceAll(":","").substring(0,6)
					+ getRandomString(3);
		} while (orderRepository.findByOrderNum(orderNum).isPresent());

		return orderNum;
	}

	// 램덤 문자열 생성
	private String getRandomString(int length) {

		StringBuilder result = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int num = random.nextInt(35) + 1;
			if (num == 18 || num == 19 || num == 24 || num == 30 || num == 31) {
				i--;
			} else if (num < 10) {
				result.append(num);
			} else {
				result.append((char)(num + 55));
			}
		}
		return result.toString();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<OrderDTO> getBuyList(Long memberId, Pageable pageable) {
		return orderRepository.findAllByMemberId(memberId, pageable).map(OrderDTO::new);
	}

	@Override
	public void cancel(Long orderId) {
		orderRepository.findById(orderId).map(order -> order.update(OrderState.CANCEL)).orElse(null);
	}

}
