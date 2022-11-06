package com.youprice.onion.entity.product;

import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "town_id")
    private Long id; //동네번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinate_id")
    private Coordinate coordinate; //좌표번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //회원번호 FK
    private Member member;

    private Double wishDistance; //원하는 거리지역 상품조회

	public Town(Coordinate coordinate, Member member) {
		this.coordinate = coordinate;
		this.member = member;
	}

    public Town townCreate(TownAddDTO townAddDTO, Coordinate coordinate, Member member) {

        this.id = townAddDTO.getId();
        this.coordinate = coordinate;
        this.member = member;

        return this;
    }


}
