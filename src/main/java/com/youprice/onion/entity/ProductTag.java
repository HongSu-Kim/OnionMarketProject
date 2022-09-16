package com.youprice.onion.entity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter

public class ProductTag {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_tag_id")
    private Long id; //상품태그번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "product_id")
    private Product product; //상품번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "tag_id")
    private Tag tag; //태그번호 FK




}
