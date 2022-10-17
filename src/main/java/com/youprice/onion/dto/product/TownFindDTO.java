package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Town;
import com.youprice.onion.entity.product.Coordinate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TownFindDTO {


    private Long id; //동네번호 PK;

    private Long coordinateId; //좌표번호 FK

    private String townName; //동네이름


    private Long memberId; //회원번호 FK


    public TownFindDTO(Town town) {

        id =town.getId();
        memberId =town.getMember().getId();
        coordinateId = town.getCoordinate().getId();
        townName = town.getCoordinate().getTownName();
    }

}
