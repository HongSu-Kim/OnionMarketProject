package com.youprice.onion.service.product.impl;


import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl  implements CategoryService {

 private  final CategoryRepositoy categoryRepositoy;
 private  final  CategoryRepositoy.Categoryrepositoy categoryrepositoy;


 public void CategoryCreate(CategoryCreateDTO categoryCreatedto){
    Category category = new Category();

     category.categoryCreate1(categoryCreatedto);
     categoryRepositoy.save(category);
  categoryRepositoy.findByCategory(category);
  categoryRepositoy.save(category);



 }

 public  void CategoryDelete(CategoryUpdateDTO categoryUpdatedto){

     Category category = new Category();
     category = category.categoryUpdate(categoryUpdatedto);
     //categoryRepositoy.deleteById(categoryUpdatedto.getCategory_id());
     categoryRepositoy.deleteById(categoryUpdatedto.getCategory_id());


   //  category = categoryRepositoy.findByCategoryName(categoryUpdatedto.getCategoryName());




 }

    public  void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto){

        Category category = new Category();

        category.categoryUpdate(categoryUpdatedto);
        categoryRepositoy.save(category);

    }

 public List<Category> findCategory(){

     return categoryRepositoy.findAll();
 }

    public List<Category> finduniform(){
        return categoryrepositoy.finduniform();
    }


    public List<Category> footballboot(){
        return categoryrepositoy.findfootballboot();
    }

    public  List<Category> categoryList(){
     return categoryRepositoy.findAll();
    }

    public  List<Category> uniformPARENT_ID(){
        return categoryrepositoy.uniformPARENT_ID();
    }

    public  List<Category> footballbootPARENT_ID(){
        return categoryrepositoy.footballbootPARENT_ID();
    }


}

