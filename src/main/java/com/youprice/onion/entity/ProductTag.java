package com.youprice.onion.entity;

@Entity
public class ProductTag {

        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "product_tag_id")
        private Long id; //상품태그번호 PK

         @ManyToOne
         @JoinColumn(name = "product_id")
         private Product product; //상품번호 FK

         @ManyToOne
         @JoinColumn(name = "tag_id")
         private Tag tag; //태그번호 FK




}
