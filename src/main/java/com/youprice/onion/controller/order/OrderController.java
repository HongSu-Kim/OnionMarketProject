package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.DeliveryService;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
public class OrderController {

	private final OrderService orderService;
	private final DeliveryService deliveryService;
	private final MemberService memberService;
	private final HttpSession httpSession;

	// 주문 페이지
	@GetMapping("order")
	public String order(Model model, OrderAddDTO orderAddDTO, Long productId) {

//		User user = (User) httpSession.getAttribute("loginMember");
//		Member loginMember = (Member) httpSession.getAttribute("loginMember");

//		MemberDTO memberDTO = memberService.getMemberDTO(loginMember.getId()););
		MemberDTO memberDTO = memberService.getMemberDTO(1L);//--
//		ProductDTO productDTO = productService.getProductDTO(productId);
		ProductDTO productDTO = null;//--

        orderAddDTO.setOrderNum(orderService.getOrderNum());

		model.addAttribute("memberDTO", memberDTO);
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
	public String buyList(Model model) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");
//		Long memberId = loginMember.getId();

//		List<OrderDTO> list = orderService.getBuyList(memberId);
		List<OrderDTO> list = orderService.getBuyList(1L);//--

		model.addAttribute("list", list);
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
	public String sellList(Model model) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");
//		Long memberId = loginMember.getId();

//		List<OrderDTO> list = orderService.getSellList(memberId);
		List<OrderDTO> list = orderService.getSellList(1L);//--

		model.addAttribute("list", list);
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
