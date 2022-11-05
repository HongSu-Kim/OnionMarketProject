package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("prohibitionkeyword")
public class ProhibitionKeywordController {

    private final ProhibitionKeywordService prohibitionKeywordService;
    private final MemberService memberService;


    @GetMapping("prohibitionkeyword")
    public String ProhibitionKeywordAdd(Model model, @LoginUser SessionDTO sessionDTO, HttpSession session, HttpServletResponse response) throws IOException {

        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO", memberDTO);

        List<ProhibitionKeywordFindDTO> prohibitionKeywordList = prohibitionKeywordService.prohibitionKewordList();

        model.addAttribute("prohibitionKeywordList", prohibitionKeywordList);

        System.out.println(session.getAttribute("categoryList"));


        return "member/Addprohibitionkeyword";
    }

    @PostMapping("prohibitionkeyword")

    public String ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO, HttpServletResponse response) throws IOException {


        prohibitionKeywordService.ProhibitionKeywordAdd(prohibitionKeywordAddDTO, response);

        return "redirect:/prohibitionkeyword/prohibitionkeyword";
    }


    @PostMapping("prohibitionkeywordDelete")

    public String prohibitionkeywordDelete(Model model, @RequestParam("id") Long prohibitionkeywordId) {

        prohibitionKeywordService.ProhibitionKeywordDelete(prohibitionkeywordId);


        return "redirect:/prohibitionkeyword/prohibitionkeyword";
    }


}
