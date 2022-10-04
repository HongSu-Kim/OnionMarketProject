package com.youprice.onion.entity.order;

import com.youprice.onion.entity.member.Address;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;//주문번호 PK FK

    @Embedded
    private Address address;//배송지

    private int delivery_cost;//배송비
    private String request;//요청사항

}
