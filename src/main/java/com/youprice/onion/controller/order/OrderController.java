package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductSellListDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.DeliveryService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.util.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {

	private final OrderService orderService;
	private final DeliveryService deliveryService;
	private final MemberService memberService;
	private final ProductService productService;
	private final PaymentService paymentService;

	// 주문 페이지
	@GetMapping("order")
	public String order(@LoginUser SessionDTO sessionDTO, Model model, OrderAddDTO orderAddDTO, Long productId) {
		if (sessionDTO == null) return "redirect:/member/login";

		MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
		ProductDTO productDTO = productService.getProductDTO(productId);
        orderAddDTO.setOrderNum(orderService.getOrderNum());
		orderAddDTO.setDeliveryCost(100);//--

		if (productDTO == null) {
			return  "redirect:/";//
		}

		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/order";
	}

	// 주문
	@PostMapping("order")
	@ResponseBody
	public ResponseEntity<?> order(@RequestBody OrderAddDTO orderAddDTO) throws IOException {
		try{
			Long orderId = orderService.addOrder(orderAddDTO);
			return new ResponseEntity<>("redirect:/order/complete?orderId=" + orderId, HttpStatus.OK);

			// DB 입력 오류시 결제취소
		} catch (Exception e) {
			try {
				log.error("데이터베이스 입력오류입니다 : " + e.toString());
				paymentService.paymentCancel(orderAddDTO);
			} catch (Exception e2) {
				log.error("결제 취소중 오류입니다 : " + e.toString());
				return new ResponseEntity<>("결제 취소중 오류입니다\n고객센터에 문의하세요", HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<>("오류가 발생해 결제가 취소되었습니다.", HttpStatus.SERVICE_UNAVAILABLE);
		}
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
	@GetMapping("detail")
	public String buyDetail(@LoginUser SessionDTO sessionDTO, Model model, Long orderId) {

		DeliveryDTO deliveryDTO = deliveryService.getDeliveryDTO(orderId);

		if (deliveryDTO.getOrderDTO().getMemberId() != sessionDTO.getId()) {
			return "redirect:/product/main";
		}

		model.addAttribute("deliveryDTO", deliveryDTO);
		return "order/detail";
	}

	// 판매 내역 조회 페이지
	@GetMapping("sellList")
	public String sellList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault Pageable pageable) {
		if (sessionDTO == null) return "redirect:/member/login";

		Page<ProductSellListDTO> page = productService.getProductSellListDTO(sessionDTO.getId(), pageable);

		model.addAttribute("page", page);
		return "order/sellList";
	}

	// 상품 상세 페이지
//	// 판매 내역 상세 페이지
//	@GetMapping("sellDetail")
//	public String sellDetail(Model model, Long orderId) {
//
//		DeliveryDTO deliveryDTO = deliveryService.getDeliveryDTO(orderId);
//
//		model.addAttribute("deliveryDTO", deliveryDTO);
//		return "order/sellDetail";
//	}

}
