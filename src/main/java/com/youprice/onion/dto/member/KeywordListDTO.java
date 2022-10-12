package com.youprice.onion.dto.member;


import com.youprice.onion.entity.member.Keyword;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeywordListDTO {



    private Long id; //키워드 번호 PK

    private Long memberId;//회원번호 FK

    private String keywordName;//키워드명


    public KeywordListDTO(Keyword keyword) {

        id = keyword.getId();
        memberId = keyword.getMember().getId();
        keywordName = keyword.getKeywordName();
    }

}
