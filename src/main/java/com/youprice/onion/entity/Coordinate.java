package com.youprice.onion.entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Coordinate {

        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "coordinate_id")
        private Long id; //좌표번호 PK

        private String townName; //동네명

        private String longitude; //경도

        private String latitude; //위도

        @OneToMany(mappedBy = "coordinate") //동네번호
        private List<Town> townList = new ArrayList<>();


}
