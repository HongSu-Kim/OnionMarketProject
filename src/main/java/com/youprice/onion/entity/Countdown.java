package com.youprice.onion.entity;

@Entity
public class Countdown {

           @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
           @Column(name = "countdown_id")
           private Long id; //카운트다운번호 PK

           @OneToOne
           @JoinColumn(name = "product_id")
           private Product product; //상품번호 FK




}
