package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryCreateDTO {


    private Long product_category_id; //상품카테고리번호 PK


    private int product_id;//상품번호 FK


    private Long category_id;//카테고리번호 FK


}
