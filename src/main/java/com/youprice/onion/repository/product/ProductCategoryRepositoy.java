package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


public interface ProductCategoryRepositoy extends JpaRepository<ProductCategory,Long> {

   @Repository
   @RequiredArgsConstructor

    class ProductCategoryrepositoy{

        private final EntityManager em;


    }



}
