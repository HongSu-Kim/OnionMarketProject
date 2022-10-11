package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.order.OrderDeliveryDTO;
import com.youprice.onion.dto.order.OrderProductDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.DeliveryService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

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
//		ProductDTO productDTO = productService.getProductDTO(productId);

        orderAddDTO.setOrderNum(orderService.getOrderNum());

		model.addAttribute("MemberDTO", memberDTO);
//		model.addAttribute("productDTO", productDTO);
		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/order";
	}

	// 주문
	@PostMapping("order")
	@ResponseBody
	public String order(HttpServletResponse response, @RequestBody OrderAddDTO orderAddDTO) {

		Long orderId = orderService.addOrder(orderAddDTO);

		return "redirect:/order/complete?orderId=" + orderId;
	}

	// 완료 페이지
	@GetMapping("complete")
	public String completion(Model model, Long orderId) {
		OrderDeliveryDTO orderDeliveryDTO = orderService.getOrderDeliveryDTO(orderId);
		model.addAttribute("orderDTO", orderDeliveryDTO);
		return "order/complete";
	}

	// 구매 내역 조회 페이지
	@GetMapping("buyList")
	public String buyList(Model model) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");
//		Long memberId = loginMember.getId();

//		List<OrderDTO> list = orderService.getBuyList(memberId);
		List<OrderProductDTO> list = orderService.getBuyList(1L);//--

		model.addAttribute("list", list);
		return "order/buyList";
	}

	// 구매 내역 상세 페이지
	@GetMapping("buyDetail")
	public String buyDetail(Model model, Long orderId) {

		OrderDeliveryDTO orderDeliveryDTO = orderService.getOrderDeliveryDTO(orderId);

		model.addAttribute("orderDTO", orderDeliveryDTO);
		return "order/buyDetail";
	}

	// 판매 내역 조회 페이지
	@GetMapping("sellList")
	public String sellList(Model model) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");
//		Long memberId = loginMember.getId();

//		List<OrderDTO> list = orderService.getSellList(memberId);
		List<OrderProductDTO> list = orderService.getSellList(1L);//--

		model.addAttribute("list", list);
		return "order/sellList";
	}
	
	// 판매 내역 상세 페이지
	@GetMapping("sellDetail")
	public String sellDetail(Model model, Long orderId) {

		OrderDeliveryDTO orderDeliveryDTO = orderService.getOrderDeliveryDTO(orderId);

		model.addAttribute("orderDTO", orderDeliveryDTO);
		return "order/sellDetail";
	}

}
