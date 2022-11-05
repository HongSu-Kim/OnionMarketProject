package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
public class CategoryAddDTO {


    private Long category_id; //카테고리번호 PK

    @NotEmpty(message = "카테고리 이름을 쓰세요!")
    private String categoryName; //상위카테고리이름

    @NotEmpty(message = "카테고리 이름을 쓰세요!")
    private String subCategoryName; //하위카테고리이름


    private Category category; //하위카테고리번호




}
