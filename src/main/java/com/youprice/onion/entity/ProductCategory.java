package com.youprice.onion.entity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductCategory {

      @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
      @Column(name = "product_category")
      private Long id; //상품카테고리번호 PK

      @ManyToOne(fetch = FetchType.LAZY )
      @JoinColumn(name = "product_id")
      private Product product;//상품번호 FK

      @ManyToOne(fetch = FetchType.LAZY )
      @JoinColumn(name = "category_id")
      private Category category;//카테고리번호 FK




}
