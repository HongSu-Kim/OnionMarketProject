package com.youprice.onion.entity.order;

import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private Long id;//주문번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;//상품번호 FK

    private int orderPrice;//주문가격

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderRole orderRole;//구분-buyer,seller

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderState orderState;//주문상태-order,delivery,cancel,complete

    private LocalDateTime orderDate;//주문시간
    private LocalDateTime modifiedDate;//수정시간


    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @OneToOne(mappedBy = "order")
    private Review review;


//    public Order(OrderAddDTO orderAddDTO, ) {
//        this.member =
//    }
}
