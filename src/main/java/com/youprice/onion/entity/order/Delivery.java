package com.youprice.onion.entity.order;

import com.youprice.onion.entity.member.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    @JoinColumn(name = "order_id")
    private Long id;
    @MapsId
    @OneToOne
    private Order order;//주문번호 PK FK

    @Embedded
    private Address address;//배송지

    private String request;//요청사항
    private int deliveryCost;//배송비

}
