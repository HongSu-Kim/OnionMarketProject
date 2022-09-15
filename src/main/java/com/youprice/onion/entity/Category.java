package com.youprice.onion.entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
       private Long id; //카테고리번호 PK

        private String categoryName; //카테고리이름

        @ManyToOne
        @JoinColumn(name = "parent_id") //부모카테고리번호
        private Category category;

        @OneToMany(mappedBy = "category") //자식카테고리번호
        private  List<Category> categoryList = new ArrayList<>();

        @OneToMany(mappedBy = "category")//상품카테고리번호
        private List<ProductCategory> productCategoryList = new ArrayList<>();

        @OneToMany(mappedBy = "category")//회원카테고리번호
        private List<MemberCategory> memberCategoryList = new ArrayList<>();






}
