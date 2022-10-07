package com.youprice.onion.dto.order;

import java.time.LocalDateTime;

public class OrderReadDTO {

        private Long orderId;//주문번호 PK
        private Long memberId;//회원번호 FK
        private Long productId;//상품번호 FK
        private int orderPrice;//주문가격
        private String orderRole;//구분-buyer,seller
        private String orderState;//주문상태-order,delivery,cancel,complete
        private LocalDateTime orderDate;//주문시간
        private LocalDateTime modifiedDate;//수정시간

//        private Delivery delivery;
//        private Review review;

}
