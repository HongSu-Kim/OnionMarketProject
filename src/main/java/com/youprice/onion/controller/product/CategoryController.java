package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("category")
public class CategoryController {

    private  final CategoryServiceImpl categoryServiceImpl;



    @GetMapping("category")
    public String create1(Model model){

        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();
        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);

        return "product/category";
    }

    @PostMapping("category")
    public  String create(CategoryCreateDTO categoryCreatedto, Category category, Model model){



        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();

        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        categoryServiceImpl.CategoryCreate(categoryCreatedto);

        return "redirect:/category/category";
    }

    @GetMapping("categoryupdate")
    public String create(Model model){


        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();
        List<Category> uniformPARENT_ID = categoryServiceImpl.uniformPARENT_ID();
        List<Category> footballbootPARENT_ID = categoryServiceImpl.footballbootPARENT_ID();

        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        model.addAttribute("uniformPARENT_ID",uniformPARENT_ID);
        model.addAttribute("footballbootPARENT_ID",footballbootPARENT_ID);

        return "product/categorydelete";
    }
    @PostMapping("categoryupdate")
    public  String update(Model model, CategoryUpdateDTO categoryUpdatedto)
                       {

        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();

        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);


        categoryServiceImpl.CategoryDelete(categoryUpdatedto);

        return "redirect:/category/category";

    }


}
