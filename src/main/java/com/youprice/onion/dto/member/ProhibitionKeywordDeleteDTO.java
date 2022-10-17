package com.youprice.onion.dto.member;


import com.youprice.onion.entity.member.ProhibitionKeyword;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProhibitionKeywordDeleteDTO {



    private Long id; //금지키워드 번호 PK


    private String prohibitionKeywordName;//금지키워드명




}
