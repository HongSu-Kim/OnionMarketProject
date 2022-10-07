package com.youprice.onion.entity.product;

import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Town {
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "town_id")
        private Long id; //동네번호 PK

        @ManyToOne(fetch = FetchType.LAZY )
        @JoinColumn(name = "coordinate_id")
        private Coordinate coordinate; //좌표번호 FK

        @ManyToOne(fetch = FetchType.LAZY )
        @JoinColumn(name ="member_id") //회원번호 FK
        private Member member;


        public Town townCreate(TownFindDTO townFinddto , Coordinate coordinate, Member member){

            this.id = townFinddto.getId();
            this.coordinate = coordinate;
            this.member =member;

            return this;
        }


}
