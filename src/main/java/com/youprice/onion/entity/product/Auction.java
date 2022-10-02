package com.youprice.onion.entity.product;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "auction_id")
    private Long id; //경매번호 PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //상품번호 FK
    
    private LocalDateTime auctionDeadline; //경매기한
    private String auctionStatus; //경매현황
    
}
