package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchFindDTO {


    private Long id; //키워드 번호 PK

    private String searchName;//검색명

    private int searchCount; //검색수

}
