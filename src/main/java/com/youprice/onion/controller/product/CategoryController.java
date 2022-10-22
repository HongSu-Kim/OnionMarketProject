package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Path;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("category") //카테고리조회
    public String CategoryAdd(Model model,CategoryAddDTO categoryAddDTO) {

        List<Category> category =categoryService.findTopCategory();

        model.addAttribute("category",category);
        model.addAttribute("categoryAddDTO",categoryAddDTO);


        return "product/category";
    }
//    @PostMapping("category") //카테고리생성
//    public String CategoryAdd(Category category, Model model
//    , @Valid @ModelAttribute CategoryAddDTO categoryAddDTO, BindingResult bindingResult) {
//
//
//        if(bindingResult.hasErrors()){
//           model.addAttribute("category",categoryService.findTopCategory());
//            return "product/category";
//        }
//
//    else
//
//
//
//        return "redirect:/category/category";
//    }
    @PostMapping("category") //카테고리생성
    public String CategoryAdd(CategoryAddDTO categoryCreatedto, Category category, Model model, @RequestParam("topcategoryName")
                         String topcategoryName, @RequestParam("categoryName")String categoryName,HttpServletResponse response)throws IOException {


        if(categoryName =="") { //상위카테고리생성

            categoryService.TopCategoryAdd(categoryCreatedto,topcategoryName,response);



        }

        else  //하위카테고리생성

            categoryService.SubCategoryAdd(categoryCreatedto, topcategoryName,response);




        return "redirect:/category/category";
    }

    @GetMapping("categoryupdate") //카테고리 수정
    public String create(Model model) {

        List<Category> Topcategory =categoryService.findTopCategory();
       // List<CategoryFindDTO> Subcategory = categoryService.findSubCategory();

        model.addAttribute("Topcategory",Topcategory);
       // model.addAttribute("Subcategory",Subcategory);

        return "product/categoryupdate";
    }



    @PostMapping("categoryupdate") //카테고리 삭제
    public String update (@RequestParam("id") Long id) {




        categoryService.CategoryDelete(id);

        return "redirect:/category/categoryupdate";

    }


}
