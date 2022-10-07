package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.SearchCreateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.SearchRepositoy;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import com.youprice.onion.service.product.impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("search")
@RequiredArgsConstructor
@Controller
public class SearchController {


    private  final MemberServiceImpl memberServiceImpl;
    private  final CategoryServiceImpl categoryServiceImpl;

    private  final SearchServiceImpl searchServiceImpl;

    private  final SearchRepositoy searchRepositoy;
    private  final SearchRepositoy.Searchrepositoy searchrepositoy;


    @GetMapping("/search")
    public String SearchCreate(Model model){

//        List<Category> finduniform = categoryServiceImpl.finduniform();
//        List<Category> footballboot = categoryServiceImpl.footballboot();
//
//        model.addAttribute("finduniform",finduniform);
//        model.addAttribute("footballboot",footballboot);
        return "product/search";
    }

    @PostMapping("/search")
    public String KeywordCreate(Model model, SearchCreateDTO searchCreateDto, @RequestParam("userId")String userId,
                                @RequestParam("searchName") String searchName ){

        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();


        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);

        if(searchRepositoy.findBySearchName(searchName) ==null || searchServiceImpl.Searchcount()==0) {

            searchServiceImpl.SearchCreate(searchCreateDto,searchName);
        }



        else
        searchrepositoy.updatecount(searchName);


        return "keyword";
    }


}
