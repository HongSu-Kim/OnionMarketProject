package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoy categoryRepositoy;
    private final CategoryRepositoy.Categoryrepositoy categoryrepositoy;

    @Override
    public void TopCategoryAdd(CategoryAddDTO categoryCreatedto, String topcategoryName) { //상위카테고리 생성
        Category category = new Category();

        Optional<Category> DuplicatechecktopcategoryName = categoryRepositoy.findByCategoryName(topcategoryName);
        if (DuplicatechecktopcategoryName.isPresent()) {
            System.out.println("이미 존재하는 상위카테고리입니다!");
            return;
        } else

            category.TopcategoryAdd(categoryCreatedto, topcategoryName);

        categoryRepositoy.save(category);
        System.out.println("상위카테고리 생성완료!");

    }

    @Override
    public void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName) { //하위카테고리생성
        Category category = new Category();
        categoryAddDTO.setCategory(categoryRepositoy.findByCategoryName(topcategoryName).orElse(null));

        Optional<Category> DuplicatechecksubcategoryName =
                categoryRepositoy.findByCategoryNameAndCategory(categoryAddDTO.getCategoryName(), categoryAddDTO.getCategory());
        if (DuplicatechecksubcategoryName.isPresent()) {
            System.out.println("이미 존재하는 하위카테고리입니다!");
            return;
        }
        category.SubcategoryAdd(categoryAddDTO);
        categoryRepositoy.save(category);
        System.out.println("하위카테고리 생성완료!");

    }

    @Override
    public void CategoryDelete(Long id) { //카테고리삭제
        Category category = new Category();
        categoryRepositoy.deleteById(id);

    }

    @Override
    public void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto) { //카테고리수정
        Category category = new Category();
//
//        category.categoryUpdate(categoryUpdatedto);
//        categoryRepositoy.save(category);
    }

    @Override
    public List<Category> findTopCategory() {


        return categoryrepositoy.findTopCategory(); //parent_id가 null인 상위카테고리조회
    }

    @Override
    public List<Category> findSubCategory() { //parent_id가 null이 아닌 카테고리조회

        return  categoryrepositoy.findSubcategory();


    }


}

