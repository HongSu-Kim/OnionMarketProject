package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ProductService productService;
	private final HttpSession httpSession;

	// 주문 페이지
	@GetMapping("order")
	public String order(Model model, OrderAddDTO orderAddDTO, Long productId) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");

//		MemberDTO memberDTO = memberService.getMemberDTO(loginMember.getId()););
		MemberDTO memberDTO = memberService.getMemberDTO(1L);
		ProductDTO productDTO = productService.getProductDTO(productId);

        orderAddDTO.setOrderNum(orderService.getOrderNum());

		model.addAttribute("MemberDTO", memberDTO);
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/order";
	}

	// 주문
	@PostMapping("order")
	@ResponseBody
	public String order(HttpServletResponse response, @RequestBody OrderAddDTO orderAddDTO) {

		orderService.addOrder(orderAddDTO);

		return "/order/completion";
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
