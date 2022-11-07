package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Path;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepositoy categoryRepositoy;


    @GetMapping("category") //카테고리조회
    public String CategoryAdd(Model model, HttpSession session) {

        List<CategoryFindDTO> category =categoryService.findTopCategory();

        model.addAttribute("topCategory", category);


        return "product/category";
    }

    @PostMapping("category") //카테고리생성
    public String CategoryAdd(CategoryAddDTO categoryCreatedto, Model model, @RequestParam("categoryName") String categoryName,
                              HttpSession session, @RequestParam("subCategoryName") String subCategoryName, HttpServletResponse response) throws IOException {

        if (subCategoryName == "") { //상위카테고리생성

            List<CategoryFindDTO> categoryList = categoryService.topCategoryAdd(categoryCreatedto, categoryName, session, response);


        } else  //하위카테고리생성

            categoryService.SubCategoryAdd(categoryCreatedto, categoryName, subCategoryName, response);


        return "redirect:/category/category";
    }

    @GetMapping("categoryupdate") //카테고리 수정
    public String create(Model model,HttpSession session) {


        List<CategoryFindDTO> topCategoryList = categoryService.findTopCategory();
        model.addAttribute("topCategoryList", topCategoryList);
        model.addAttribute("topCategoryList2", topCategoryList);

        return "product/categoryupdate";
    }
    @GetMapping("/category/{topCategoryId}") //하위 카테고리 호출
    @ResponseBody
    public List<CategoryFindDTO> findSubCategory(@PathVariable("topCategoryId") Long topCategoryId) {
        List<CategoryFindDTO> subCategory = categoryService.findSubCategory(topCategoryId);

        return subCategory;
    }

    @GetMapping("categoryDelete") //카테고리 삭제
    public String update(@RequestParam("categoryId") Long categoryId) {

        List<CategoryFindDTO> categoryFindDTOList = categoryService.findSubCategory(categoryId);
        System.out.println(categoryId);



          categoryService.CategoryDelete(categoryId);



        return "redirect:/category/categoryupdate";

    }


}
