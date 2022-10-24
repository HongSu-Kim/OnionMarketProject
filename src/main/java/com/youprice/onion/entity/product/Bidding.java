package com.youprice.onion.entity.product;


import com.youprice.onion.dto.product.BiddingAddDTO;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Bidding {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bidding_id")
    private Long id; //입찰번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //상품번호FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호FK
    
    private int bid; //입찰가
    private LocalDateTime biddingTime; //입찰시간

    public Bidding(Product product, Member member, BiddingAddDTO biddingAddDTO) {

        this.product = product;
        this.member = member;
        this.bid = biddingAddDTO.getBid();
        this.biddingTime = LocalDateTime.now();
    }

}
