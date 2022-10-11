package com.youprice.onion.dto.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoordinateAddDTO {


    private Long id; //좌표번호 PK

    private String townName; //동네명

    private String latitude; //위도

    private String longitude; //경도





}
