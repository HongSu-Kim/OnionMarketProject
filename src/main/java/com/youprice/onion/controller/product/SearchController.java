package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.entity.product.Search;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.service.product.SearchService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("search")
public class SearchController {

    private  final MemberService memberService;
    private  final CategoryService categoryService;
    private  final SearchService  searchService;
    private  final  ProductService productService;
    private  final  ProhibitionKeywordService prohibitionKeywordService;

    @GetMapping("search")
    public String SearchCreate(Model model){


       List<Search> popularSearch = searchService.findBySearchRank();

       model.addAttribute("popularSearch",popularSearch);

        return "product/popularSearch";
    }

    @GetMapping("result")
    public String KeywordCreate(Model model, SearchAddDTO searchAddDTO,
                           @RequestParam("searchName") String searchName, HttpServletResponse response) throws IOException {

        try {
            if (searchService.findBySearchName(searchName) == null) {


                searchService.SearchCreate(searchAddDTO, searchName, response);

            } else
                searchService.searchupdatecount(searchName);
            List<ProductListDTO> searchList = productService.getSearchList(searchName, searchName);

            model.addAttribute("searchList", searchList);

            return "product/main";

        } catch (RuntimeException e) {
            return AlertRedirect.warningMessage(response, "검색중 오류입니다\n");

        }


    }


}
