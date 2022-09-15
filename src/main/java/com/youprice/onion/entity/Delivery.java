package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
public class Delivery implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;//주문번호 PK FK

    @Embedded
    private Address address;//배송지

    private int delivery_cost;//배송비

    private String request;//요청사항

}
