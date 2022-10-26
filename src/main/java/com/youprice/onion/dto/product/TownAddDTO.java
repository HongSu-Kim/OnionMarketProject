package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownAddDTO {


    private Long id; //동네번호 PK;

    private Long coordinateId; //좌표번호 FK

    private String townName; //좌표번호 FK

    private Long memberId; //회원번호 FK



}
