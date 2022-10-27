package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.ComplainService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("complain")
public class ComplainController {

    private final ComplainService complainService;
    private final MemberService memberService;
    private final ProductService productService;
    //private final ChatroomService chatroomService;

    @GetMapping
    public String complainHome(){
        return "board/complainMain";
    }

    @GetMapping("created/{id}")
    public String complainForm(@PathVariable("id") Long productId, Model model,
                               @LoginUser SessionDTO sessionDTO){
        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            ProductDTO productDTO = productService.getProductDTO(productId);
            MemberDTO targetDTO = memberService.getMemberDTO(productDTO.getMemberId());

            model.addAttribute("targetDTO",targetDTO);
            model.addAttribute("memberDTO", memberDTO);
            model.addAttribute("productDTO", productDTO);
        } else return "member/login";

        return "board/complainForm";
    }

    // 상품상세페이지나, 채팅페이지에서 complain/created로 넘어옴
    @PostMapping("/created")
    public String createdComplain(@ModelAttribute ComplainFormDTO complainFormDTO){
        complainService.saveComplain(complainFormDTO);
        //return "redirect:/product/productdetail/" + productId;
        return "redirect:/complain/list";
    }

    @GetMapping("/list")
    public String lists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @LoginUser SessionDTO sessionDTO, Model model) {
        if(sessionDTO == null) return "member/login";

        Page<ComplainDTO> complainList = complainService.findAll(pageable);

        int pageNumber = complainList.getPageable().getPageNumber();
        int totalPages = complainList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("complainList", complainList);

        return "board/complainList";
    }

    @GetMapping("/modify/{complainId}")
    public String modifyStatus(@PathVariable Long complainId, String select,
                               HttpServletResponse response) throws IOException {
        String result = complainService.modifyStatus(complainId, select);
        if(result.equals("접수취소")) {
            return AlertRedirect.warningMessage(response, "/complain/list", "신고 접수를 취소하였습니다.");
        }
        return "redirect:/complain/list";
    }

}
