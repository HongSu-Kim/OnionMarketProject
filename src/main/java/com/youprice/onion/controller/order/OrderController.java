package com.youprice.onion.controller.order;

import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;

	// 주문 페이지
	@GetMapping("order")
	public String order() {
		return "order/order";
	}

	// 결제 페이지
	@GetMapping("payment")
	public String payment() {
		return "order/payment";
	}

	// 완료 페이지
	@GetMapping("completion")
	public String completion() {
		return "order/completion";
	}

	// 구매 내역 조회 페이지
	@GetMapping("buyList")
	public String buyList() {
		return "order/buyList";
	}

	// 구매 내역 상세 페이지
	@GetMapping("buyDetail")
	public String buyDetail() {
		return "order/buyDetail";
	}

	// 판매 내역 조회 페이지
	@GetMapping("sellList")
	public String sellList() {
		return "order/sellList";
	}
	
	// 판매 내역 상세 페이지
	@GetMapping("sellDetail")
	public String sellDetail() {
		return "order/sellDetail";
	}

}
