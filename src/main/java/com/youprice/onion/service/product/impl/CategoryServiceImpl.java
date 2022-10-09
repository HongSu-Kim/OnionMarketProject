package com.youprice.onion.service.product.impl;


import com.youprice.onion.dto.product.CategoryCreateDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoy categoryRepositoy;
    private final CategoryRepositoy.Categoryrepositoy categoryrepositoy;

    @Override
    public void TopCategoryCreate(CategoryCreateDTO categoryCreatedto, String topcategoryName) {
        Category category = new Category();

        Optional<Category> DuplicatechecktopcategoryName = categoryRepositoy.findByCategoryName(topcategoryName);
        if (DuplicatechecktopcategoryName.isPresent()) {
            System.out.println("이미 존재하는 상위카테고리입니다!");
            return;
        } else

            category.TopcategoryCreate(categoryCreatedto, topcategoryName);

        categoryRepositoy.save(category);
        System.out.println("상위카테고리 생성완료!");

    }

    @Override
    public void SubCategoryCreate(CategoryCreateDTO categoryCreateDTO, String topcategoryName) {
        Category category = new Category();
        categoryCreateDTO.setCategory(categoryrepositoy.findtopcategoryName(topcategoryName));

        Optional<Category> DuplicatechecksubcategoryName =
                categoryRepositoy.findByCategoryNameAndCategory(categoryCreateDTO.getCategoryName(),categoryCreateDTO.getCategory());
        if (DuplicatechecksubcategoryName.isPresent()) {
            System.out.println("이미 존재하는 하위카테고리입니다!");
            return;
        }
        category.SubcategoryCreate(categoryCreateDTO);
        categoryRepositoy.save(category);
        System.out.println("하위카테고리 생성완료!");

    }

    @Override
    public void CategoryDelete(Long id) { //카테고리삭제
        Category category = new Category();
        categoryrepositoy.deleteCategory(id);


    }

    @Override
    public void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto) {
        Category category = new Category();

        category.categoryUpdate(categoryUpdatedto);
        categoryRepositoy.save(category);
    }

    @Override
    public List<Category> findCategory() {


        return categoryrepositoy.findCategory();
    }

    @Override
    public List<Category> findSubCategory() {
        return categoryrepositoy.findSubcategory();
    }

    @Override
    public List<Category> finduniform() {
        return null;
    }

    @Override
    public List<Category> footballboot() {
        return categoryrepositoy.findfootballboot();
    }

    @Override
    public List<Category> categoryList() {
        return categoryRepositoy.findAll();
    }

    @Override
    public List<Category> uniformPARENT_ID() {
        return categoryrepositoy.uniformPARENT_ID();
    }

    @Override
    public List<Category> footballbootPARENT_ID() {
        return categoryrepositoy.footballbootPARENT_ID();
    }


}

