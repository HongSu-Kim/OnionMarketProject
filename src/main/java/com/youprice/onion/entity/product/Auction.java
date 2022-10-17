package com.youprice.onion.entity.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "auction_id")
    private Long id; //경매번호 PK

    @OneToOne(mappedBy = "auction")
    private Product product; //상품번호 FK
    
    private LocalDateTime auctionDeadLine; //경매기한
    private String auctionStatus; //경매현황

    public Auction(LocalDateTime auctionDeadLine, String auctionStatus){

        this.auctionDeadLine = LocalDateTime.now().plusDays(3);
        this.auctionStatus = auctionStatus;

    }

}
