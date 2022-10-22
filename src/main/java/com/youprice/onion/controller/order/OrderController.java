package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.DeliveryService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.util.PaymentService;
import com.youprice.onion.util.AlertRedirect;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	@GetMapping("payment/{productId}")
	public String payment(@LoginUser SessionDTO sessionDTO, Model model, @ModelAttribute OrderAddDTO orderAddDTO, @PathVariable Long productId,
						  HttpServletResponse response) throws IOException {
		if (sessionDTO == null) return "redirect:/member/login";
		if (productId == null) return "redirect:/product/main";

		MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
		ProductDTO productDTO = productService.getProductDTO(productId);
        orderAddDTO.setOrderNum(orderService.getOrderNum());

		if (productDTO == null) {
			return AlertRedirect.warningMessage(response, "/product/main", "상품정보가 존재하지 않습니다.");
		} else if (productDTO.getMemberId() == sessionDTO.getId()) {
			return AlertRedirect.warningMessage(response, "/product/detail/" + productId, "자신의 상품은 구매할수 없습니다.");
		} else if (productDTO.getProductProgress() != ProductProgress.TRADINGS) {
			return AlertRedirect.warningMessage(response, "/product/main", "구매할 수 없는 상품입니다.");
		}

		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("orderAddDTO", orderAddDTO);
		return "order/payment";
	}

	// 주문 - 양파페이 결제
	@PostMapping("payment")
	public String payment(@ModelAttribute OrderAddDTO orderAddDTO) throws IOException {
		Long orderId = orderService.addOrder(orderAddDTO);
		return "redirect:/order/complete/" + orderId;
	}

	// 주문 - imp 결제
	@PostMapping("imp-payment")
	@ResponseBody
	public ResponseEntity<?> impPayment(@RequestBody OrderAddDTO orderAddDTO) {
		try{
			Long orderId = orderService.addOrder(orderAddDTO);
			return new ResponseEntity<>("/order/complete/" + orderId, HttpStatus.OK);

			// DB 입력 오류시 결제취소
		} catch (Exception e) {
			try {
				log.error("데이터베이스 입력오류입니다 : " + e.toString());
				paymentService.paymentCancel(orderAddDTO.getImp_uid(), orderAddDTO.getOrderNum(), orderAddDTO.getOrderPayment());
			} catch (IOException ioe) {
				log.error("결제 취소중 오류입니다 : " + ioe.toString());
				return new ResponseEntity<>("결제 취소중 오류입니다\n고객센터에 문의하세요", HttpStatus.SERVICE_UNAVAILABLE);
			}
			return new ResponseEntity<>("오류가 발생해 결제가 취소되었습니다.", HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	// 완료 페이지
	@GetMapping("complete/{orderId}")
	public String completion(Model model, @PathVariable Long orderId) {
		OrderDTO orderDTO = orderService.getOrderDTO(orderId);
		model.addAttribute("orderDTO", orderDTO);
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
	@GetMapping({ "detail/{orderId}", "detail/{orderId}/{mode}" })
	public String buyDetail(@LoginUser SessionDTO sessionDTO, Model model, @PathVariable Long orderId,
							@PathVariable(required = false) String mode, HttpServletResponse response) throws IOException {
		if (sessionDTO == null) return "redirect:/member/login";

		OrderDTO orderDTO = orderService.getOrderDTO(orderId);

		if(orderDTO == null) {
			return AlertRedirect.warningMessage(response, "/order/buyList", "주문번호가 존재하지 않습니다.");
		} else if (!orderDTO.getMemberId().equals(sessionDTO.getId())) {
			return AlertRedirect.warningMessage(response, "/order/buyList", "자신의 구매 내역만 확인가능합니다.");
		}

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("mode", mode);
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

	// 주문 취소
	@GetMapping("cancel/{orderId}")
	public String cancel(@PathVariable Long orderId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			
			// 주문취소
			orderService.cancel(orderId);
			
			// 결제 취소 오류
		} catch (RuntimeException e) {
			return AlertRedirect.warningMessage(response, "결제 취소중 오류입니다\n고객센터에 문의하세요");
		}
		return "redirect:" + request.getHeader("Referer");
	}

	// 배송지 변경
	@PostMapping("update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody DeliveryDTO deliveryDTO) {
		try {

			// 배송지 변경
			deliveryService.update(deliveryDTO);
			return new ResponseEntity<>("배송 정보가 수정되었습니다.", HttpStatus.OK);

			// 오류
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
