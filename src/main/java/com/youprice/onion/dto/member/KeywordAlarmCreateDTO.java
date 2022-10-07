package com.youprice.onion.dto.member;


import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class KeywordAlarmCreateDTO {

    private Long id; //키워드 번호 PK

    private Member member;//회원번호 FK

    private Keyword keyword;//키워드명 FK
}
