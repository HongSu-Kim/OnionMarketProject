package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryFindDTO {


    private Long id; //카테고리번호 PK

    private Long categoryId; //하위카테고리번호

    private String categoryName; //하위카테고리이름

    public CategoryFindDTO(Category category) {

        id =category.getId();
        categoryId = category.getParent().getId();
        categoryName = category.getCategoryName();
    }







}
