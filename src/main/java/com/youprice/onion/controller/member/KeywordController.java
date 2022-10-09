package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.entity.member.Keyword;
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


    //!! 아이디 일시적으로 asd로설정
    private final CategoryService categoryService;

    private final KeywordService keywordService;

    @GetMapping("keyword")
    public String KeywordCreate(Model model) {
        List<Category> finduniform = categoryService.finduniform();
        List<Category> footballboot = categoryService.footballboot();

        model.addAttribute("finduniform", finduniform);
        model.addAttribute("footballboot", footballboot);
        return "product/keyword";
    }

    @PostMapping("keyword")
    public String KeywordCreate(Model model, KeywordCreateDTO keywordCreateDto, @RequestParam("userId") String userId
            , @RequestParam("keywordName") String keywordName) {


        List<Category> finduniform = categoryService.finduniform();
        List<Category> footballboot = categoryService.footballboot();

        model.addAttribute("finduniform", finduniform);
        model.addAttribute("footballboot", footballboot);


        keywordService.KeywordCreate(keywordCreateDto, userId, keywordName);


        return "redirect:/keyword/keyword";
    }


    @GetMapping("mykeyword")
    public String KeywordAlarm(Model model, @RequestParam("userId") String userId) {

        keywordService.KeywordList(model, userId);

        return "product/mykeyword";
    }


}
