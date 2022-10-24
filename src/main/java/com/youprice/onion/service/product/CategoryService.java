package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CategoryService {


     void TopCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName, HttpServletResponse response)throws IOException;
    void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName,HttpServletResponse response)throws IOException;
    void CategoryDelete(Long id);

    void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto);

    List<Category> findTopCategory();//상위 카테고리찾기

    List<Category> findSubCategory();//상위 카테고리찾기

   // List<CategoryFindDTO> findSubCategory(Long categoryId); //하위 카테고리찾기

    List<CategoryFindDTO> CategoryIdFind(String name);

}
