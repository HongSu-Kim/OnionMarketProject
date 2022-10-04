package com.youprice.onion.controller.order;

import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("wish")
public class WishController {

    private final WishService wishService;

    // 찜 목록 페이지
    @GetMapping("wishList")
    public String wishList() {
        return "wish/wishList";
    }

    // 찜 추가
    @PostMapping("addWish")
    public String addWish() {
        return ":/wish/wishList";
    }

    // 찜 삭제
    @PostMapping("removeWish")
    public String removeWish() {
        return "redirect:/wish/wishList";
    }

}
