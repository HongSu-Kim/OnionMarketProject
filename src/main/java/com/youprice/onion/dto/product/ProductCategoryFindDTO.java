package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryFindDTO {


    private Long id; //상품카테고리번호 PK


    private Product product;//상품번호 FK


    private Category category;//카테고리번호 FK


}
