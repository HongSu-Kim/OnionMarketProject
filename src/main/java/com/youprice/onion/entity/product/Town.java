package com.youprice.onion.entity.product;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Coordinate;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class Town {
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "town_id")
        private Long id; //동네번호 PK

        @ManyToOne(fetch = FetchType.LAZY )
        @JoinColumn(name ="member_id") //회원번호 FK
        private Member member;

        @ManyToOne(fetch = FetchType.LAZY )
        @JoinColumn(name = "coordinate_id")
        private Coordinate coordinate; //좌표번호 FK

}
