package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.ProhibitionKeywordAddDTO;
import com.youprice.onion.dto.member.ProhibitionKeywordDeleteDTO;
import com.youprice.onion.dto.member.ProhibitionKeywordFindDTO;
import com.youprice.onion.dto.member.ProhibitionKeywordUpdateDTO;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("prohibitionkeyword")
public class ProhibitionKeywordController {

    private final ProhibitionKeywordService prohibitionKeywordService;


    @GetMapping("prohibitionkeyword")
    public String ProhibitionKeywordAdd(Model model) {


        return "member/Addprohibitionkeyword";
    }

    @PostMapping("prohibitionkeyword")

        public String ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO){



         prohibitionKeywordService.ProhibitionKeywordAdd(prohibitionKeywordAddDTO);

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
