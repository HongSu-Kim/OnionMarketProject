package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;

import java.util.List;

public interface CategoryService {


     void TopCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName);
    void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName);
    void CategoryDelete(Long id);

    void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto);

    List<Category> findTopCategory();//상위 카테고리찾기

    List<Category> findSubCategory(); //하위 카테고리찾기

    List<CategoryFindDTO> CategoryIdFind(String name);

}
