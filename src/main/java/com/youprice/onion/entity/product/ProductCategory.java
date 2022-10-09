package com.youprice.onion.entity.product;

import com.youprice.onion.dto.product.ProductCategoryCreateDTO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductCategory {

      @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
      @Column(name = "product_category_id")
      private Long id; //상품카테고리번호 PK

      @ManyToOne(fetch = FetchType.LAZY )
      @JoinColumn(name = "product_id")
      private Product product;//상품번호 FK

      @ManyToOne(fetch = FetchType.LAZY )
      @JoinColumn(name = "category_id")
      private Category category;//카테고리번호 FK


      public  ProductCategory productCategoryCreate(Product product, Category category, ProductCategoryCreateDTO productCategorydto) {



          this.id = productCategorydto.getProduct_category_id();
          this.product = product;
          this.category = category;


            return this;



      }



}
