package com.youprice.onion.entity.product;


import com.youprice.onion.dto.product.SearchCreateDTO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Search {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "search_id")
    private Long id; //검색 번호 PK

    private String searchName;//키워드명

    private int count; //검색수


    public Search SearchCreate(SearchCreateDTO searchCreateDto){

    this.searchName = searchCreateDto.getSearchName();
    this.count = 1;

    return this;
    }








}

