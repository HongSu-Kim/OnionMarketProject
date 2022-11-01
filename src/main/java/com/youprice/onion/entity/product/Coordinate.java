package com.youprice.onion.entity.product;
import java.util.ArrayList;
import java.util.List;

import com.youprice.onion.dto.product.CoordinateAddDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Coordinate {

        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "coordinate_id")
        private Long id; //좌표번호 PK


        private String townName; //동네명

        private Double latitude; //위도

        private Double longitude; //경도



        @OneToMany(mappedBy = "coordinate") //동네번호
        private List<Town> townList = new ArrayList<>();
        public Coordinate coordinateAdd(CoordinateAddDTO coordinateAddDTO){

                this.id = coordinateAddDTO.getId();
                this.townName = coordinateAddDTO.getTownName();
                this.latitude = coordinateAddDTO.getLatitude();
                this.longitude = coordinateAddDTO.getLongitude();

                return this;
        }


}

