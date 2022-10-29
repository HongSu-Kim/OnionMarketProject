package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Delivery;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.util.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final DeliveryRepository deliveryRepository;
	private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
	private final PaymentService paymentService;

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

		// 양파페이 결제
		if (orderAddDTO.getImp_uid() == null)
			if (member.subCash(orderAddDTO.getOrderPayment()) < 0){
				log.error("양파페이 부족");
				throw new RuntimeException();
			}
		memberRepository.save(member);

		// 상품상태 변경 - tradings
		product.progressUpdate(ProductProgress.TRADINGS);
		productRepository.save(product);

		// 주문내역 생성
		Order order = new Order(member, product, orderAddDTO.getOrderNum(), orderAddDTO.getImp_uid(), orderAddDTO.getOrderPayment());
		Long orderId = orderRepository.save(order).getId();

		// 배송정보 생성
		if (orderAddDTO.isDelivery()) {
			Delivery delivery = new Delivery(order, orderAddDTO);
			deliveryRepository.save(delivery);
		}

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

	// 구매 내역 조회
	@Override
	@Transactional(readOnly = true)
	public Page<OrderDTO> getBuyList(Long memberId, Pageable pageable) {

		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
				pageable.getPageSize(), Sort.Direction.DESC, "id");

		return orderRepository.findAllByMemberId(memberId, pageable).map(OrderDTO::new);
	}

	// 주문 취소
	@Override
	public void cancel(Long orderId) {
		orderRepository.findById(orderId).map(order -> {
			
			// 주문 상태 변경 - cancel
			order.update(OrderState.CANCEL);

			// 상품 상태 변경 - sales on
			order.getProduct().progressUpdate(ProductProgress.SALESON);

			// imp 결제시 환불
			if (order.getImp_uid() != null){
				try {
					paymentService.paymentCancel(order.getImp_uid(), order.getOrderNum(), order.getOrderPayment());
				} catch (IOException ioe) {
					log.error("결제 취소중 오류입니다 : " + ioe.toString());
					throw new RuntimeException();
				}
				
				// 양파페이 결제시 환불
			} else {
				order.getMember().addCash(order.getOrderPayment());
			}
			
			return order;
		}).orElse(null);
	}

}
