package com.youprice.onion.entity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Keyword {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "keyword_id")
    private Long id; //키워드 번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "product_id")
    private Product product;//상품번호 FK



    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String keywordName;//키워드명











}

