package com.youprice.onion.entity.member;


import com.youprice.onion.dto.member.ProhibitionKeywordAddDTO;
import com.youprice.onion.dto.member.ProhibitionKeywordUpdateDTO;
import com.youprice.onion.dto.product.CategoryAddDTO;
import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.entity.product.Category;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProhibitionKeyword {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "prohibition_keyword_id")
    private Long id; //금지키워드 번호 PK


    private String prohibitionKeywordName; //금지키워드명



    public ProhibitionKeyword prohibitionKeywordAdd (ProhibitionKeywordAddDTO prohibitionKeywordAddDTO) {
        this.id = prohibitionKeywordAddDTO.getId();
        this.prohibitionKeywordName = prohibitionKeywordAddDTO.getProhibitionKeywordName();

        return this;

    }

    public ProhibitionKeyword prohibitionKeywordUpdate (ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO) {

        this.prohibitionKeywordName = prohibitionKeywordUpdateDTO.getProhibitionKeywordName();

        return this;

    }






}

