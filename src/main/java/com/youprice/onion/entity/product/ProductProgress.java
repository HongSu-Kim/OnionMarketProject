package com.youprice.onion.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductProgress {

	SALESON("판매중"),
    RESERVED("예약중"),
	TRADINGS("거래중"),
	SOLDOUT("판매완료"),
	BLIND("신고된 상품");

	private String kor;

}
