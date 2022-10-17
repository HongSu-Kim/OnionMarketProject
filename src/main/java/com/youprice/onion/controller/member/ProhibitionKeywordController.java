package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.*;
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
    public String ProhibitionKeywordAdd(Model model, @LoginUser SessionDTO sessionDTO, HttpServletResponse response)throws IOException {

        if (sessionDTO == null) return "redirect:/member/login";


        return "member/Addprohibitionkeyword";
    }

    @PostMapping("prohibitionkeyword")

        public String ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO,HttpServletResponse response)throws IOException{



         prohibitionKeywordService.ProhibitionKeywordAdd(prohibitionKeywordAddDTO,response);

            return "redirect:/prohibitionkeyword/prohibitionkeyword";
        }

    @GetMapping("prohibitionkeywordUpdate")

    public String prohibitionkeywordUpdate(Model model){

        List<ProhibitionKeywordFindDTO> prohibitionKeywordFindDTOList = prohibitionKeywordService.prohibitionKewordList();

        model.addAttribute("prohibitionKeywordFindDTOList",prohibitionKeywordFindDTOList);

        return "member/prohibitionkeywordUpdate";
    }

    @PostMapping("prohibitionkeywordUpdate")

    public String prohibitionkeywordUpdate(ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO
    ,@RequestParam("updatekeyword")String updatekeyword) {


        prohibitionKeywordService.ProhibitionKeywordUpdate(prohibitionKeywordUpdateDTO,updatekeyword);

        return "redirect:/prohibitionkeyword/prohibitionkeywordUpdate";
    }



    @GetMapping("prohibitionkeywordDelete")

    public String prohibitionkeywordDelete(Model model){

        List<ProhibitionKeywordFindDTO> prohibitionKeywordFindDTOList = prohibitionKeywordService.prohibitionKewordList();

        model.addAttribute("prohibitionKeywordFindDTOList",prohibitionKeywordFindDTOList);

        return "member/prohibitionkeywordDelete";
    }

    @PostMapping("prohibitionkeywordDelete")
    public String prohibitionkeywordDelete(Model model, @RequestParam("prohibitionKeywordName")String prohibitionKeywordName){

        prohibitionKeywordService.ProhibitionKeywordDelete(prohibitionKeywordName);

        return "redirect:/prohibitionkeyword/prohibitionkeywordDelete";
    }



    }
