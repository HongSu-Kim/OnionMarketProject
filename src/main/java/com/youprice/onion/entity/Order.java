package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;//주문번호

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;//회원번호
    private int memberId;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;//상품번호
    private int productId;//상품번호

    @Enumerated(EnumType.STRING)
    private OrderRole orderRole;//buyer,seller

    @Enumerated(EnumType.STRING)
    private OrderState orderState;//order,delivery,cancel,complete

    private LocalDateTime orderDate;//주문시간
    private LocalDateTime modifiedDate;//수정시간


    @OneToOne(mappedBy = "order")
    private Delivery delivery;

}
