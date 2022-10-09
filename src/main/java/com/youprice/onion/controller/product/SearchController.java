package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.SearchCreateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.SearchRepositoy;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.SearchService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import com.youprice.onion.service.product.impl.SearchServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("search")
public class SearchController {

    private  final MemberService memberService;
    private  final CategoryService categoryService;
    private  final SearchService  searchService;

    @GetMapping("search")
    public String SearchCreate(Model model){

        return "product/search";
    }

    @PostMapping("search")
    public String KeywordCreate(Model model, SearchCreateDTO searchCreateDto, @RequestParam("userId")String userId,
                                @RequestParam("searchName") String searchName ){

        if(searchService.findBySearchName(searchName)==null || searchService.Searchcount()==0) {

            searchService.SearchCreate(searchCreateDto,searchName);
        }

        else
            searchService.searchupdatecount(searchName);

        return "redirect:/search/search";
    }


}
