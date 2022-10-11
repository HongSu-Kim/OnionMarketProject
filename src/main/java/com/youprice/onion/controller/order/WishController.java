package com.youprice.onion.controller.order;

import com.youprice.onion.dto.order.WishProductDTO;
import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("wish")
public class WishController {

    private final WishService wishService;
	private final HttpSession httpSession;

    // 찜 목록 페이지
    @GetMapping("wishList")
    public String wishList(Model model) {

//		Member loginMember = (Member) httpSession.getAttribute("loginMember");

//		List<Wish> list = wishService.getWishList(logonMember.getId());
		List<WishProductDTO> list = wishService.getWishList(1L);//--

		model.addAttribute("list", list);
        return "wish/wishList";
    }

    // 찜 추가
    @GetMapping("addWish")
    public String addWish(Long productId) {
//		Member loginMember = (Member) httpSession.getAttribute("loginMember");

		wishService.addWish(1L, 1L);//--
//		wishService.addWish(loginMember.getId(), productId);
        return "redirect:/wish/wishList";
    }

    // 찜 삭제
    @PostMapping("removeWish")
    public String removeWish() {
        return "redirect:/wish/wishList";
    }

}
