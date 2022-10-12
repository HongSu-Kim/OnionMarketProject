package com.youprice.onion.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("ProhibitionKeyword")
public class ProhibitionKeywordController {




    @GetMapping("ProhibitionKeyword")
    public String ProhibitionKeywordAdd(Model model) {


        return "product/ProhibitionKeyword";
    }



}
