package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class CategoryUpdateDTO {



    private Long id; //카테고리번호 PK


    private String categoryName; //상위카테고리이름


    private Category category; //하위카테고리번호




}
