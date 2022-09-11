package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer wishId;//찜번호

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;//회원번호
    private int memberId;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;//상품번호
    private int productId;//상품번호

    private LocalDateTime createdDate;//등록시간

}
