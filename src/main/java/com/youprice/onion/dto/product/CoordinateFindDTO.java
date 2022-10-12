package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordinateFindDTO {


    private Long id; //좌표번호 PK

    private String townName; //동네명

    public CoordinateFindDTO(Coordinate coordinate) {

        id =coordinate.getId();
        townName = coordinate.getTownName();
    }




}
