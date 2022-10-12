package com.youprice.onion.controller.product;


import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.ProductCategoryCreateDTO;
import com.youprice.onion.dto.product.ProductCategoryFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.ProductCategoryService;
import com.youprice.onion.service.product.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor


@Controller
@RequestMapping("productcategory")
public class ProductCategoryController {

    private  final ProductCategoryService productCategoryService;
    private  final CategoryService categoryService;


@GetMapping("productcategory") //상품카테고리 조회
    public String productcategory(Model model){

    List<Category> productcategory =categoryService.findTopCategory();

    model.addAttribute("productcategory",productcategory);

    return "product/productcategory";
}

@GetMapping("productcategoryList")//해당카테고리 입력시 조회
    public  String productcategoryFind(Model model, @RequestParam("categoryName")String categoryName){

    List<Category> productcategory =categoryService.findTopCategory();
  //  List<CategoryFindDTO> categoryList =  categoryService.findCategoryId(categoryName);

    model.addAttribute("productcategory",productcategory);
   // model.addAttribute("categoryList",categoryList);

    return "product/productcategoryList";


}

    @PostMapping("productcategoryList")//해당카테고리 입력시 조회
    public  String productcategoryFindproduct(Model model,@RequestParam("productName")String productName
    ,@RequestParam("categoryName")String categoryName){

        List<Category> productcategory =categoryService.findTopCategory();
      //  List<Category> categoryList =  categoryService.findCategoryId(categoryName);

        model.addAttribute("productcategory",productcategory);
        // model.addAttribute("categoryList",categoryList);

        System.out.println(productName);



        return "product/productcategoryList";


    }





















}
