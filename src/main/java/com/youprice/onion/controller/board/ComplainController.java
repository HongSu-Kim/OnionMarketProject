package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.ComplainService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("complain")
public class ComplainController {

    private final ComplainService complainService;
    private final ProductService productService;
    //private final ChatroomService chatroomService;

    @GetMapping
    public String complainHome(){
        return "board/complainMain";
    }

    // 테스트
    @GetMapping("created/{id}")
    public String complainForm(@PathVariable("id") Long productId, Model model,
                               @LoginUser SessionDTO sessionDTO){
        //ProductDTO productDTO = productService.findById(productId);
        if(sessionDTO != null){
            model.addAttribute("sessionDTO", sessionDTO);
        }
        model.addAttribute("productId", productId);
        return "board/complainForm";
    }

    // 상품상세페이지나, 채팅페이지에서 complain/created로 넘어옴
    @PostMapping("/created")
    public String createdComplain(@ModelAttribute ComplainFormDTO complainFormDTO){
        Long productId = complainFormDTO.getProductId();
        System.out.println("complainFormDTO = " + complainFormDTO.getComplainType());
        System.out.println("complainFormDTO = " + complainFormDTO.getComplainContent());

        complainService.saveComplain(complainFormDTO);
        //return "redirect:/product/productdetail/" + productId;
        return "redirect:/complain/";
    }

    /* 목록
    @GetMapping("/list")
    public String lists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String field,
                        @RequestParam(required = false, defaultValue = "") String word,
                        @LoginUser SessionDTO sessionDTO, Model model) {
        if(sessionDTO != null){
            model.addAttribute("sessionDTO", sessionDTO);
        }

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
    }*/

}
