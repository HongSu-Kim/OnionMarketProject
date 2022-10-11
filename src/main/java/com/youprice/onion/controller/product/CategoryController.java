package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("category")
    public String create1(Model model) {

        List<Category> category =categoryService.findCategory();

        model.addAttribute("category",category);
        

        return "product/category";
    }

    @PostMapping("category")
    public String create(CategoryCreateDTO categoryCreatedto, Category category, Model model, @RequestParam("topcategoryName")
                         String topcategoryName,@RequestParam("categoryName")String categoryName) {


        if(categoryName =="") { //상위카테고리생성

            categoryService.TopCategoryCreate(categoryCreatedto,topcategoryName);

        }

        else //하위카테고리생성
            categoryService.SubCategoryCreate(categoryCreatedto, topcategoryName);




        return "redirect:/category/category";
    }

    @GetMapping("categoryupdate")
    public String create(Model model) {

        List<Category> Topcategory =categoryService.findCategory();
        List<Category> Subcategory = categoryService.findSubCategory();

        model.addAttribute("Topcategory",Topcategory);
        model.addAttribute("Subcategory",Subcategory);

        return "product/categoryupdate";
    }

    @PostMapping("categoryupdate")
    public String update (@RequestParam("id") Long id) {




        categoryService.CategoryDelete(id);

        return "redirect:/category/categoryupdate";

    }


}
