package com.youprice.onion.dto.member;


import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeywordCreateDTO {



    private Long id; //키워드 번호 PK

    private Member member;//회원번호 FK

    private String keywordName;//키워드명



}
