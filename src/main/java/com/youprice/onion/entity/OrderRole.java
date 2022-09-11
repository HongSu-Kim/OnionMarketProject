package com.youprice.onion.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderRole {

    BUYER("ROLE_BUYER", "판매자"),
    SELLER("ROLE_SELLER", "구매자");

    private final String key;
    private final String value;

}
