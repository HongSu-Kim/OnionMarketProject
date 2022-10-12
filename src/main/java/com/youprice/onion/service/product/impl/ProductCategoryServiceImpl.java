package com.youprice.onion.service.product.impl;



import com.youprice.onion.dto.product.ProductCategoryCreateDTO;
import com.youprice.onion.dto.product.ProductCategoryFindDTO;
import com.youprice.onion.entity.product.ProductCategory;
import com.youprice.onion.repository.product.CategoryRepositoy;
import com.youprice.onion.repository.product.ProductCategoryRepositoy;
import com.youprice.onion.service.product.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepositoy productCategoryRepositoy;
    private  ProductCategoryRepositoy.ProductCategoryrepositoy productCategoryrepositoy;
    private CategoryRepositoy.Categoryrepositoy categoryrepositoy;

    @Override
    public void ProductCategoryFind(ProductCategoryFindDTO productCategoryFindDTO) {

    //  categoryrepositoy.findSubcategory(productCategoryFindDTO);




    }
}














