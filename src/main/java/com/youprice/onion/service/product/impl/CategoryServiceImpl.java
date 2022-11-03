package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.member.ProhibitionKeyword;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepositoy categoryRepositoy;
    private final CategoryRepositoy.Categoryrepositoy categoryrepositoy;

    @Override
    public void TopCategoryAdd(CategoryAddDTO categoryCreatedto, String topcategoryName, HttpServletResponse response) throws IOException { //상위카테고리 생성
        Category category = new Category();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Optional<Category> DuplicatechecktopcategoryName = categoryRepositoy.findByCategoryName(topcategoryName);
        if (DuplicatechecktopcategoryName.isPresent()) {
            out.println("<script>alert('이미존재하는 상위키워드입니다 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }

        if (topcategoryName == "") {

            out.println("<script>alert('공백입니다 상위키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        } else

            category.TopcategoryAdd(categoryCreatedto, topcategoryName);

        categoryRepositoy.save(category);
        out.println("<script>alert('상위카테고리 생성완료!');history.go(-1); </script>");
        out.flush();

    }


    @Override
    public void SubCategoryAdd(CategoryAddDTO categoryAddDTO, String topcategoryName, HttpServletResponse response) throws IOException { //하위카테고리생성
        Category category = new Category();
        categoryAddDTO.setCategory(categoryRepositoy.findByCategoryName(topcategoryName).orElse(null));
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Optional<Category> DuplicatechecksubcategoryName =
                categoryRepositoy.findByCategoryNameAndParent(categoryAddDTO.getCategoryName(), categoryAddDTO.getCategory());
        if (DuplicatechecksubcategoryName.isPresent()) {
            out.println("<script>alert('이미존재하는 하위키워드입니다 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }
        if (topcategoryName == "") {
            out.println("<script>alert('상위카테고리가 공백입니다 상위키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;

        }

        if (categoryAddDTO.getCategoryName() == "") {

            out.println("<script>alert('공백입니다 하위키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }

        category.SubcategoryAdd(categoryAddDTO);
        categoryRepositoy.save(category);
        out.println("<script>alert('하위카테고리 생성완료!');history.go(-1); </script>");
        out.flush();


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
    public List<CategoryFindDTO> CategoryIdFind(String name) { //카테고리이름으로 카테고리조회

        Category category = new Category();
        category = categoryRepositoy.findAllByCategoryName(name);
        System.out.println(category.getId()); //114


        return categoryRepositoy.findAllByParentId(category.getId())
                .stream().map(CategoryFindDTO::new)
                .collect(Collectors.toList());


        // @Override
// public List<TownFindDTO> townList(Long memberId) {
//
//  return townRepositoy.findAllByMemberId(memberId)
//          .stream().map(town -> new TownFindDTO(town))
//          .collect(Collectors.toList());
// }


    }

    @Override
    public List<Category> findTopCategory() {


        return categoryrepositoy.findTopCategory(); //parent_id가 null인 상위카테고리조회
    }

//    @Override

    @Override
    public List<Category> findSubCategory() {  //parent_id가 null이 아닌 카테고리조회
        return categoryrepositoy.findSubcategory();
    }


    public List<CategoryFindDTO> findSubCategory(Long categoryId) { // parent_id가 null이 아닌 상위카테고리에 해당하는 하위 카테고리조회

        return categoryRepositoy.findByParentId(categoryId)
                .stream().map(CategoryFindDTO::new)
                .collect(Collectors.toList());

    }



}

