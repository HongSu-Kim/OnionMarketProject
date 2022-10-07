package com.youprice.onion.controller.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;
//	private final ProductService productService;
	private final HttpSession httpSession;

	// 주문 페이지
	@GetMapping("order")
	public String order(Model model, OrderAddDTO orderAddDTO, Long orderId) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");
//		ProductDTO productDTO = productService.getProduct;

//		orderAddDTO.setMemberId(loginMember.getId());
//		orderAddDTO.setProductId(productDTO.getId());
		orderAddDTO.setMemberId(1L);//--
		orderAddDTO.setProductId(1L);//--

		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/order";
	}

	// 주문 페이지
	@PostMapping("order")
	public String order(Model model, @Valid OrderAddDTO orderAddDTO, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "order/order";
		}

		orderService.addOrder(orderAddDTO);

		return "redirect:/order/order";
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
