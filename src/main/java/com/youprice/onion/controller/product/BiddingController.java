package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.BiddingAddDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.product.BiddingService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("bid")
public class BiddingController {

    private final BiddingService biddingService;

    @GetMapping("bidding/{productId}")//입찰 주소
    public String bidding(Model model, @LoginUser SessionDTO sessionDTO, HttpServletResponse response) throws IOException{

        return "bidding";
    }

    @PostMapping("bidding") //실제 입찰
    public String biddingProduct(@LoginUser SessionDTO sessionDTO, @PathVariable("productId") Long productId,
                                 @ModelAttribute BiddingAddDTO biddingAddDTO, Model model) {

        biddingAddDTO.setMemberId(sessionDTO.getId());

        biddingService.bidProduct(biddingAddDTO);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail/"+productId;//상품 상세페이지로 이동
    }

}
