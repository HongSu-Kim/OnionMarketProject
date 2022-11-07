package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface CategoryService {


  List<CategoryFindDTO> topCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName, HttpSession session, HttpServletResponse response) throws IOException;

    void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName, String subCategoryName, HttpServletResponse response) throws IOException;

   Category CategoryDelete(Long categoryId);

    void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto);

    List<CategoryFindDTO> findTopCategory();//전체 상위 카테고리 조회

    List<CategoryFindDTO> findSubCategory(Long categoryId); //특정 상위카테고리에 해당하는 하위 카테고리찾기

    List<CategoryFindDTO> CategoryIdFind(String name);



}
