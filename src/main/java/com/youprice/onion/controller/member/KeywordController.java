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
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.member.impl.KeywordServiceImpl;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private final ProhibitionKeywordService prohibitionKeywordService;

    @GetMapping("keyword")
    public String KeywordCreate(Model model, @LoginUser SessionDTO sessionDTO) {


        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO",memberDTO);

        List<KeywordListDTO> MykeywordList = keywordService.KeywordList(sessionDTO.getId());

        model.addAttribute("MykeywordList",MykeywordList);

        return "product/keyword";
    }


    @PostMapping("keyword")
    public String KeywordCreate(Model model, HttpServletResponse response
    , @Valid KeywordCreateDTO keywordCreateDto, BindingResult bindingResult,@LoginUser SessionDTO sessionDTO)throws IOException{


        if(prohibitionKeywordService.ProhibitionKeywordFind(keywordCreateDto.getKeywordName()) ) { //금지키워가있으면 true

            bindingResult.addError(new FieldError("keywordCreateDto","keywordName","금지어입니다 다시입력!!"));
            System.out.println(keywordCreateDto.getKeywordName());


            if (bindingResult.hasErrors()) {

                if (sessionDTO == null) return "redirect:/member/login";
                MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

                model.addAttribute("memberDTO",memberDTO);

                List<KeywordListDTO> MykeywordList = keywordService.KeywordList(sessionDTO.getId());

                model.addAttribute("MykeywordList",MykeywordList);

                return "product/keyword";

            }

        }

        else {

            keywordService.KeywordCreate(keywordCreateDto, response);

        }
        return "redirect:/keyword/keyword";
    }



    @PostMapping("kewordDelete")
    public String MyKewordDelete(Model model,@RequestParam("id") Long keywordId) {

        keywordService.KewordDelete(keywordId);



        return "redirect:/keyword/keyword";
    }


}
