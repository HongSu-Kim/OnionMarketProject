package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wish_id")
    private Integer id;//찜번호 Pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;//상품번호 FK

    private LocalDateTime createdDate;//등록시간

}
