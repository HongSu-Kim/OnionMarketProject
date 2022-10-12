package com.youprice.onion.entity.product;


import com.youprice.onion.dto.product.SearchAddDTO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Search {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "search_id")
    private Long id; //검색 번호 PK

    private String searchName;//키워드명

    private int searchCount; //검색수


    public Search SearchAdd(SearchAddDTO searchAddDTO){

    this.searchName = searchAddDTO.getSearchName();
    this.searchCount = 1;

    return this;
    }








}

