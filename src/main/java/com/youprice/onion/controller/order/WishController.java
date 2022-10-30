package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.WishListDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.order.WishService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("wish")
@Slf4j
public class WishController {

    private final WishService wishService;

    // 찜 목록 페이지
    @GetMapping("list")
    public String wishList(@LoginUser SessionDTO sessionDTO, Model model,
						   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		if (sessionDTO == null) return "redirect:/member/login";

		Page<WishListDTO> page = wishService.getWishList(sessionDTO.getId(), pageable);

		model.addAttribute("page", page);
        return "order/wishList";
    }

	// 찜 추가 - post
	@PostMapping("addWish")
	@ResponseBody
	public ResponseEntity<?> addWishPost(@LoginUser SessionDTO sessionDTO, @RequestParam Long productId) {
		if (sessionDTO == null) return new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		try {
			wishService.addWish(sessionDTO.getId(), productId);
			return new ResponseEntity<>("찜 목록에 추가헀습니다.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("상품이 존재하지 않습니다.", HttpStatus.FORBIDDEN);
		}
	}

	// 찜 추가 - get
	@GetMapping("addWish/{productId}")
	public String addWishGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long productId, HttpServletResponse response) throws IOException {
		if (sessionDTO == null) return "redirect:/member/login";

		try {
			wishService.addWish(sessionDTO.getId(), productId);
			return AlertRedirect.warningMessage(response, "/product/detail/" + productId, "찜 목록에 추가헀습니다.");
		} catch (Exception e) {
			return AlertRedirect.warningMessage(response, "/", "상품이 존재하지 않습니다.");
		}
	}

    // 찜 삭제 - delete
    @DeleteMapping("removeWish")
	@ResponseBody
    public ResponseEntity<?> removeWish(@LoginUser SessionDTO sessionDTO, @RequestParam Long productId) {
		if (sessionDTO == null) return new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		wishService.removeWish(sessionDTO.getId(), productId);

		return new ResponseEntity<>("찜 목록에서 삭제헀습니다.", HttpStatus.OK);
    }

	// 찜 삭제 - get
	@GetMapping("removeWish/{productId}")
	@ResponseBody
	public String removeWishGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long productId, HttpServletResponse response) throws IOException {
		if (sessionDTO == null) return "redirect:/member/login";

		wishService.removeWish(sessionDTO.getId(), productId);

		return AlertRedirect.warningMessage(response, "/product/detail/" + productId, "찜 목록에서 삭제헀습니다.");
	}

}
