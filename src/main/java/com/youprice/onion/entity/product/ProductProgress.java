package com.youprice.onion.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductProgress {

	TRADINGS("거래중"),
    RESERVED("예약중"),
	SOLDOUT("판매완료");

	private String kor;

}
