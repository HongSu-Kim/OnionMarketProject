package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private  final CategoryRepositoy categoryRepositoy;
    private  final CategoryServiceImpl categoryServiceImpl;
   // private  final  ProductImageServiceImpl productImageService;


    @GetMapping("/category")
    public String create1(Model model){


        return "category";
    }

    @PostMapping("/main/admin/{userId}")
    public  String create(CategoryCreateDTO categoryCreatedto, Category category, Model model){


       // List<ProductImage> list = productImageService.findProduct();
        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();

       // model.addAttribute("list",list);
        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        categoryServiceImpl.CategoryCreate(categoryCreatedto);

        return "category";
    }

    @GetMapping("/categorymain")
    public String create(Model model){

        List<Category> list = categoryServiceImpl.findCategory();

        model.addAttribute("list",list);

        return "categorymain";
    }


    @GetMapping("/main/categoryUpdate/{userId}")
    public  String update(Model model){

        List<Category> categoryList = categoryServiceImpl.categoryList();

        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();
        List<Category> uniformPARENT_ID = categoryServiceImpl.uniformPARENT_ID();
        List<Category> footballbootPARENT_ID = categoryServiceImpl.footballbootPARENT_ID();


        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("uniformPARENT_ID",uniformPARENT_ID);
        model.addAttribute("footballbootPARENT_ID",footballbootPARENT_ID);

        return "categorydelete";

    }

    @PostMapping("/main/categoryUpdate/{userId}")
    public  String update(Model model, CategoryUpdateDTO categoryUpdatedto)
                       {

        List<Category> categoryList = categoryServiceImpl.categoryList();

        List<Category> finduniform = categoryServiceImpl.finduniform();
        List<Category> footballboot = categoryServiceImpl.footballboot();
        List<Category> uniformPARENT_ID = categoryServiceImpl.uniformPARENT_ID();
        List<Category> footballbootPARENT_ID = categoryServiceImpl.footballbootPARENT_ID();




        model.addAttribute("finduniform",finduniform);
        model.addAttribute("footballboot",footballboot);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("uniformPARENT_ID",uniformPARENT_ID);
        model.addAttribute("footballbootPARENT_ID",footballbootPARENT_ID);



        categoryServiceImpl.CategoryDelete(categoryUpdatedto);

//


        return "index";

    }






}
