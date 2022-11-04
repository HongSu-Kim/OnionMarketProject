package com.youprice.onion.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"), //관리자
    USER("ROLE_USER"),
    WITHDRAWAL("ROLE_WITHDRAWAL"),
    LOCK("ROLE_LOCK");

    private String value;
}
