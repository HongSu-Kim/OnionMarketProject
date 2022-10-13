package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.DeliveryService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {

	private final OrderService orderService;
	private final DeliveryService deliveryService;
	private final MemberService memberService;
	private final ProductService productService;

	// 주문 페이지
	@GetMapping("order")
	public String order(@LoginUser SessionDTO sessionDTO, Model model, OrderAddDTO orderAddDTO, Long productId) {
		if (sessionDTO == null) return "redirect:/member/login";

		MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());//--
//		ProductDTO productDTO = productService.getProductDTO(productId);
		ProductDTO productDTO = null;//--
        orderAddDTO.setOrderNum(orderService.getOrderNum());

//		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/order";
	}

	// 주문
	@PostMapping("order")
	@ResponseBody
	public String order(@RequestBody OrderAddDTO orderAddDTO) {

		Long orderId = orderService.addOrder(orderAddDTO);

		return "redirect:/order/complete?orderId=" + orderId;
	}

	// 완료 페이지
	@GetMapping("complete")
	public String completion(Model model, Long orderId) {
		DeliveryDTO deliveryDTO = deliveryService.getDeliveryDTO(orderId);
		model.addAttribute("deliveryDTO", deliveryDTO);
		return "order/complete";
	}

	// 구매 내역 조회 페이지
	@GetMapping("buyList")
	public String buyList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault Pageable pageable) {
		if (sessionDTO == null) return "redirect:/member/login";

		Page<OrderDTO> page = orderService.getBuyList(sessionDTO.getId(), pageable);

		model.addAttribute("page", page);
		return "order/buyList";
	}

	// 구매 내역 상세 페이지
	@GetMapping("buyDetail")
	public String buyDetail(Model model, Long orderId) {

		DeliveryDTO deliveryDTO = deliveryService.getDeliveryDTO(orderId);

		model.addAttribute("deliveryDTO", deliveryDTO);
		return "order/buyDetail";
	}

	// 판매 내역 조회 페이지
	@GetMapping("sellList")
	public String sellList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault Pageable pageable) {
		if (sessionDTO == null) return "redirect:/member/login";

		Page<OrderDTO> page = orderService.getSellList(sessionDTO.getId(), pageable);

		model.addAttribute("page", page);
		return "order/sellList";
	}
	
	// 판매 내역 상세 페이지
	@GetMapping("sellDetail")
	public String sellDetail(Model model, Long orderId) {

		DeliveryDTO deliveryDTO = deliveryService.getDeliveryDTO(orderId);

		model.addAttribute("deliveryDTO", deliveryDTO);
		return "order/sellDetail";
	}

}
