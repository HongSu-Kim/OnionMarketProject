package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.entity.member.ProhibitionKeyword;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoy categoryRepositoy;

    @Override
    public List<CategoryFindDTO> topCategoryAdd(CategoryAddDTO categoryCreatedto, String categoryName, HttpSession session, HttpServletResponse response) throws IOException { //상위카테고리 생성
        Category category = new Category();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<CategoryFindDTO> categoryList = new ArrayList<>();


        if (categoryName == "") {

            out.println("<script>alert('공백입니다 상위카테고리를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return categoryList;
        }

        Optional<Category> DuplicatechecktopcategoryName = categoryRepositoy.findByCategoryName(categoryName);
        if (DuplicatechecktopcategoryName.isPresent()) {
            out.println("<script>alert('이미존재하는 상위카테고리입니다 상위카테고리를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return categoryList;
        } else

            category.TopcategoryAdd(categoryName);

        categoryRepositoy.save(category);


        out.println("<script>alert('상위카테고리 생성완료!');history.go(-1); </script>");
        out.flush();


        return categoryList;
    }


    @Override
    public void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String categoryName, String subCategoryName, HttpServletResponse response) throws IOException { //하위카테고리생성
        Category category = new Category();
        categoryAddDTO.setCategory(categoryRepositoy.findByCategoryName(categoryName).orElse(null));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        Optional<Category> DuplicatechecksubcategoryName =
                categoryRepositoy.findByCategoryNameAndParent(subCategoryName, categoryAddDTO.getCategory());

        if (subCategoryName == "") {

            out.println("<script>alert('공백입니다 하위카테고리를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }


        if (DuplicatechecksubcategoryName.isPresent()) {
            out.println("<script>alert('이미존재하는 하위카테고리입니다 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }


        category.SubcategoryAdd(categoryAddDTO, subCategoryName);
        categoryRepositoy.save(category);
        out.println("<script>alert('하위카테고리 생성완료!');history.go(-1); </script>");
        out.flush();



    }

    @Override
    public void CategoryDelete(Long categoryId) { //카테고리삭제

   categoryRepositoy.deleteAllById(categoryId);


    }

    @Override
    public void CategoryUpdate(CategoryUpdateDTO categoryUpdatedto) { //카테고리수정
//        Category category = new Category();

    }


    @Override
    public List<CategoryFindDTO> CategoryIdFind(String name) { //카테고리이름으로 카테고리조회


        Category category = categoryRepositoy.findByCategoryName(name).orElse(null);
        System.out.println(category.getId()); //114


        return categoryRepositoy.findAllByParentId(category.getId())
                .stream().map(CategoryFindDTO::new)
                .collect(Collectors.toList());


    }

    @Override
    public List<CategoryFindDTO> findTopCategory() {

        return categoryRepositoy.findTopCategory().stream().map(CategoryFindDTO::new).collect(Collectors.toList()); //parent_id가 null인 상위카테고리조회
    }


    public List<CategoryFindDTO> findSubCategory(Long categoryId) { // parent_id가 null이 아닌 상위카테고리에 해당하는 하위 카테고리조회

        return categoryRepositoy.findByParentId(categoryId)
                .stream().map(CategoryFindDTO::new)
                .collect(Collectors.toList());

    }

    public List<CategoryFindDTO> findSubCategoryName(Long categoryId) { // parent_id가 null이 아닌 상위카테고리에 해당하는 하위 카테고리조회

        return categoryRepositoy.findByParentId(categoryId)
                .stream().map(CategoryFindDTO::new)
                .collect(Collectors.toList());

    }

}

