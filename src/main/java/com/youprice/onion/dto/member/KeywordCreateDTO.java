package com.youprice.onion.dto.member;


import com.youprice.onion.dto.order.DeliveryDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeywordCreateDTO {



    private Long id; //키워드 번호 PK

    private Long memberId;//회원번호 FK

    private String keywordName;//키워드명




}
