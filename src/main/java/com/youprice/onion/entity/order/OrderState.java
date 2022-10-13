package com.youprice.onion.entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
    //주문상태-order,delivery,cancel,complete

    ORDER("주문완료"),//주문완료
    DELIVERY("배송중"),//배송중
    CANCEL("주문취소"),//주문취소
    COMPLETE("거래완료");//거래완료

	private String kor;

}
