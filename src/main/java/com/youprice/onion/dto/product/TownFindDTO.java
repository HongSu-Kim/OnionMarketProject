package com.youprice.onion.dto.product;


import com.youprice.onion.entity.product.Town;
import com.youprice.onion.entity.product.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor


public class TownFindDTO {


    private Long id; //동네번호 PK;

    private Long coordinateId; //좌표번호 FK

    private String townName; //동네이름


    private String wishtown; //원하는 동네이름

    private Double latitude; //위도

    private Double longitude; //경도


    private Long memberId; //회원번호 FK

    public TownFindDTO(Town town) {

        id = town.getId();
        memberId = town.getMember().getId();
        coordinateId = town.getCoordinate().getId();
        latitude = town.getCoordinate().getLatitude(); //위도
        longitude = town.getCoordinate().getLongitude(); //경도
        townName = town.getCoordinate().getTownName(); //동네이름


    }

}
