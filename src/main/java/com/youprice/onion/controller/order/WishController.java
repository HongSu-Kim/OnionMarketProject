package com.youprice.onion.controller.order;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.WishListDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.order.WishService;
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

@RequiredArgsConstructor
@Controller
@RequestMapping("wish")
@Slf4j
public class WishController {

    private final WishService wishService;

    // 찜 목록 페이지
    @GetMapping("list")
    public String wishList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault Pageable pageable) {
		if (sessionDTO == null) return "redirect:/member/login";

		Page<WishListDTO> list = wishService.getWishList(sessionDTO.getId(), pageable);

		model.addAttribute("list", list);
        return "order/wishList";
    }

    // 찜 추가
    @PostMapping("addWish")
	@ResponseBody
    public ResponseEntity<?> addWish(@LoginUser SessionDTO sessionDTO, @RequestParam Long productId) {
		if (sessionDTO == null) return new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		wishService.addWish(sessionDTO.getId(), productId);

        return new ResponseEntity<>("찜 목록에 추가헀습니다.", HttpStatus.OK);
    }

    // 찜 삭제
    @DeleteMapping("removeWish")
	@ResponseBody
    public ResponseEntity<?> removeWish(Long wishId) {

		wishService.removeWish(wishId);

		return new ResponseEntity<>("찜 목록에서 삭제헀습니다.", HttpStatus.OK);
    }

}
