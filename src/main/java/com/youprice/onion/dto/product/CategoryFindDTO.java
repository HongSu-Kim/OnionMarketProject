package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryFindDTO {


    private Long categoryId; //카테고리번호 PK

    private Long parentId; //상위카테고리번호

    private String categoryName; //카테고리이름

    public CategoryFindDTO(Category category) {

        categoryId = category.getId();
        if (category.getParent() != null) {
            parentId = category.getParent().getId();
        }
        categoryName = category.getCategoryName();
    }







}
