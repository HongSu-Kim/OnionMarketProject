package com.youprice.onion.entity.product;

import com.youprice.onion.entity.member.MemberCategory;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "category_id")
    private int id; //카테고리번호 PK

    private String categoryPname; //상위카테고리이름


    private String categoryCname; //하위카테고리이름

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id") //상위카테고리번호
    private Category category;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL) //하위카테고리번호
    private  List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "category")//상품카테고리번호
    private List<ProductCategory> productCategoryList = new ArrayList<>();

}
