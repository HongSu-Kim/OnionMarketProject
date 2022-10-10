package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.order.OrderDeliveryDTO;
import com.youprice.onion.dto.order.OrderProductDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Delivery;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final DeliveryRepository deliveryRepository;
	private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

	@Override
	public OrderDTO getOrderDTO(Long orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		return optional.isPresent() ? new OrderDTO(optional.get()) : null;
	}

	// 주문 완료
	@Override
	@Transactional
	public Long addOrder(OrderAddDTO orderAddDTO) {

		Member member = memberRepository.findById(orderAddDTO.getMemberId()).orElse(null);
		Product product = productRepository.findById(orderAddDTO.getProductId()).orElse(null);

		// 주문내역 생성
		Order order = new Order(member, product, orderAddDTO.getOrderPayment());
		Long orderId = orderRepository.save(order).getId();

		// 배송정보 생성
		Delivery delivery = new Delivery(orderId, orderAddDTO.getPostcode(), orderAddDTO.getAddress(), orderAddDTO.getDetailAddress(),
				orderAddDTO.getExtraAddress(), orderAddDTO.getRequest(), orderAddDTO.getDeliveryCost());
		deliveryRepository.save(delivery);

		return orderId;
	}

	// orderNum 생성
	@Override
	public String getOrderNum() {

		LocalDateTime now = LocalDateTime.now();
		String orderNum = null;

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
	public List<OrderProductDTO> getBuyList(Long memberId) {
		return orderRepository.findAllByMemberId(memberId)
				.stream().map(order -> new OrderProductDTO(order))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderProductDTO> getSellList(Long memberId) {
		return orderRepository.findAllByProductMemberId(memberId)
				.stream().map(order -> new OrderProductDTO(order))
				.collect(Collectors.toList());
	}

	// order + delivery
	@Override
	public OrderDeliveryDTO getOrderDeliveryDTO(Long orderId) {
		Optional<Order> optional = orderRepository.findById(orderId);
		return optional.isPresent() ? new OrderDeliveryDTO(optional.get()) : null;
	}

}
