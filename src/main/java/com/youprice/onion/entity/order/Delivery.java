package com.youprice.onion.entity.order;

import com.youprice.onion.entity.member.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Delivery {

    @Id
    private Long id;
    @MapsId
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;//주문번호 PK FK

    @Embedded
    private Address address;//배송지

    private String request;//요청사항
    private int deliveryCost;//배송비

    public Delivery(Order order, String postcode, String address, String detailAddress, String extraAddress, String request, int deliveryCost) {
        this.order = order;
        this.address = new Address(postcode, address, detailAddress, extraAddress);
        this.request = request;
        this.deliveryCost = deliveryCost;
    }
}
