package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;

import java.util.List;

public interface CategoryService {

   //  void CategoryCreate(CategoryCreateDTO categoryCreatedto);

     void TopCategoryCreate(CategoryCreateDTO categoryCreateDTO,String topcategoryName);
    void SubCategoryCreate(CategoryCreateDTO categoryCreateDTO,String topcategoryName);
    void CategoryDelete(Long id);

    void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto);

    List<Category> findCategory();//상단 카테고리찾기

    List<Category> findSubCategory();
    List<Category> finduniform();
    List<Category> footballboot();
    List<Category> categoryList();
    List<Category> uniformPARENT_ID();
    List<Category> footballbootPARENT_ID();
}
