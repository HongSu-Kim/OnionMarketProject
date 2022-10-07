package com.youprice.onion.entity.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
