package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordinateDeleteDTO {


    private Long category_id; //카테고리번호 PK

    private String categoryName; //상위카테고리이름


    private Category category; //하위카테고리번호




}
