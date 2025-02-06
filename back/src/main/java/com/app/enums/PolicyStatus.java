package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PolicyStatus {
    WAITING("Ожидание"),
    APPROVED("Одобрено"),
    REVISION("На доработку"),
    ;

    private final String name;
}

