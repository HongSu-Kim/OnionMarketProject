package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.service.member.impl.KeywordServiceImpl;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class KeywordController {


    private  final MemberServiceImpl memberServiceImpl;

    private  final CategoryServiceImpl categoryServiceImpl;

    private  final KeywordServiceImpl keywordServiceImpl;

    private  final  KeywordRepositoy.Keywordrepositoy keywordrepositoy;
    private  final KeywordRepositoy.Keywordrepositoy updatecount;
    private  final KeywordRepositoy keywordRepositoy;

    @GetMapping("/keyword")
    public String KeywordCreate(Model model){
        //List<ProductImage> list = productImageServiceImpl.findProduct();
        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();

        //model.addAttribute("list",list);
        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        return "keyword";
    }


    @PostMapping("/keyword")
    public String KeywordCreate(Model model, KeywordCreateDTO keywordCreateDto, @RequestParam("userId")String userId
    , @RequestParam("keywordName")String keywordName){

       // List<ProductImage> list = productImageServiceImpl.findProduct();
        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();

       // model.addAttribute("list",list);
        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);



       keywordServiceImpl.KeywordCreate(keywordCreateDto,userId,keywordName);




        return "index";
    }


    @GetMapping("/keywordAlarm")
    public String KeywordAlarm(Model model,@RequestParam("userId") String userId){

       List<Keyword> MykeywordList = keywordrepositoy.findKeywordList(userId);

       model.addAttribute("MykeywordList",MykeywordList);


        return "keywordAlarm";
    }


    @PostMapping("/keywordAlarm")
    public String KeywordAlarm(@RequestParam("userId") String userId){







        return "index";
    }




}
