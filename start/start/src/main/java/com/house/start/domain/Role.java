package com.house.start.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    CONSUMER("ROLE_CONSUMER", "소비자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    SELLER("ROLE_SELLER", "판매자");
    private final String key;
    private final String title;
}
