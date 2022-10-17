package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.KeywordService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.impl.KeywordServiceImpl;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("keyword")
public class KeywordController {


    private final CategoryService categoryService;
    private final MemberService memberService;

    private final KeywordService keywordService;

    @GetMapping("keyword")
    public String KeywordCreate(Model model, @LoginUser SessionDTO sessionDTO) {

        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO",memberDTO);

        List<KeywordListDTO> MykeywordList = keywordService.KeywordList(memberDTO.getId());

        model.addAttribute("MykeywordList",MykeywordList);

        return "product/keyword";
    }


    @PostMapping("keyword")
    public String KeywordCreate(Model model, HttpServletResponse response
    ,  KeywordCreateDTO keywordCreateDto)throws IOException{



        keywordService.KeywordCreate(keywordCreateDto,response);


        return "redirect:/keyword/keyword";
    }


//    @GetMapping("mykeyword")
//    public String MyKeword(Model model,@LoginUser SessionDTO sessionDTO) {
//
//        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
//       List<KeywordListDTO> MykeywordList = keywordService.KeywordList(memberDTO.getId());
//
//       model.addAttribute("MykeywordList",MykeywordList);
//        return "product/mykeyword";
//    }

    @PostMapping("kewordDelete")
    public String MyKewordDelete(Model model,@RequestParam("id") Long keywordId) {

        keywordService.KewordDelete(keywordId);



        return "redirect:/keyword/keyword";
    }


}
