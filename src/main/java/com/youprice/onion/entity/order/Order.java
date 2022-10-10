package com.youprice.onion.entity.order;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;//상품번호 FK

    private String orderNum;//주문번호
    private String imp_uid;//결제번호

    private int orderPayment;//결제금액

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderState orderState;//주문상태-order,delivery,cancel,complete

    private LocalDateTime orderDate;//주문시간
    private LocalDateTime modifiedDate;//수정시간


    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<Review> reviewList = new ArrayList<>();


    public Order(Member member, Product product, int orderPayment) {
        this.member = member;
        this.product = product;
        this.orderPayment = orderPayment;
        this.orderState = OrderState.ORDER;
        this.orderDate = LocalDateTime.now();
    }
    
}
