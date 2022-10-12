package com.youprice.onion.entity.member;


import com.youprice.onion.dto.product.SearchAddDTO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProhibitionKeyword {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "prohibition_keyword_id")
    private Long id; //금지키워드 번호 PK

    private String prohibitionKeywordName; //금지키워드명










}

