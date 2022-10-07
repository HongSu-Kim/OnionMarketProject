package com.youprice.onion.dto.product;


import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Coordinate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownFindDTO {


    private Long id; //동네번호 PK;

    private Coordinate coordinate; //좌표번호 FK


    private Member member; //회원번호 FK

}
