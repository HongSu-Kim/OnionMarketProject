package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.ProductDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("complain")
public class ComplainController {

    private final ComplainService complainService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("created/{productId}")
    @PreAuthorize("isAuthenticated()")
    public String complainForm(@PathVariable Long productId, Model model, @LoginUser SessionDTO sessionDTO,
                               @ModelAttribute("form") ComplainFormDTO form, HttpServletResponse response) throws IOException {
        if(sessionDTO != null){
            ProductDTO productDTO = productService.getProductDTO(productId);
            MemberDTO targetDTO = memberService.getMemberDTO(productDTO.getMemberId());

            model.addAttribute("targetDTO",targetDTO);
            model.addAttribute("productDTO", productDTO);
        } else return "redirect:/member/login";

        List<ComplainDTO> list = complainService.checkComplain(sessionDTO.getId());
        for(ComplainDTO complainDTO : list){
            if(Objects.equals(complainDTO.getProductId(), productId)){
                return AlertRedirect.warningMessage(response, "/product/detail/" + productId, "이미 신고한 게시물입니다.");
            }
        }
        return "board/complainForm";
    }

    @PostMapping("/created/{productId}")
    public String createdComplain(@Valid @ModelAttribute("form") ComplainFormDTO form, BindingResult bindingResult,
                                  @LoginUser SessionDTO sessionDTO, Model model, @PathVariable Long productId,
                                  HttpServletResponse response) throws IOException {
        if(bindingResult.hasErrors()){
            ProductDTO productDTO = productService.getProductDTO(productId);
            MemberDTO targetDTO = memberService.getMemberDTO(productDTO.getMemberId());

            model.addAttribute("targetDTO",targetDTO);
            model.addAttribute("productDTO", productDTO);
            return "board/complainForm";
        }
        ComplainDTO complainDTO = complainService.saveComplain(form);
        if(complainDTO != null){
            return AlertRedirect.warningMessage(response, "/product/detail/" + productId, "신고가 접수되었습니다.");
        }
        return "redirect:/";
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
        if(result.equals("complete")) {
            return AlertRedirect.warningMessage(response, "/complain/list", "신고를 처리하였습니다.");
        } else if (result.equals("cancle")) {
            return AlertRedirect.warningMessage(response, "/complain/list", "접수를 취소하였습니다.");
        } else if(result.equals("clear")){
            return AlertRedirect.warningMessage(response, "/complain/list", "신고 처리를 취소하였습니다.");
        }
        return "redirect:/complain/list";
    }

    //개인별 신고 리스트
    @GetMapping("/personalList/{memberId}")
    public String productList(@PathVariable Long memberId, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ComplainDTO> page = complainService.getPersonalList(memberId, pageable);
        model.addAttribute("complainList", page);
        return "board/complainList";
    }

}
