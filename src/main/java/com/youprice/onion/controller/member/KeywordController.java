package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.service.member.KeywordService;
import com.youprice.onion.service.member.impl.KeywordServiceImpl;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("keyword")
public class KeywordController {


    private final CategoryService categoryService;

    private final KeywordService keywordService;

    @GetMapping("keyword")
    public String KeywordCreate(Model model) {


        return "product/keyword";
    }


    @PostMapping("keyword")
    public String KeywordCreate(Model model, KeywordCreateDTO keywordCreateDto) {


        keywordCreateDto.setMemberId(1L);

        keywordService.KeywordCreate(keywordCreateDto);


        return "redirect:/keyword/keyword";
    }


    @GetMapping("mykeyword")
    public String KeywordAlarm(Model model) {


       List<KeywordListDTO> MykeywordList = keywordService.KeywordList(1L);

       model.addAttribute("MykeywordList",MykeywordList);
        return "product/mykeyword";
    }


}
