package com.youprice.onion.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {

    ORDER("STATE_ORDER", "주문완료"),
    DELIVERY("STATE_DELIVERY", "배달중"),
    CANCEL("STATE_CANCEL", "주문취소"),
    COMPLETE("STATE_COMPLETE", "배달완료");

    private final String key;
    private final String value;

}
