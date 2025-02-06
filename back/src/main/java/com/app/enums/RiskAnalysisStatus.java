package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiskAnalysisStatus {
    WAITING("Ожидание"),
    APPROVED("Одобрено"),
    NOT("Не одобрено"),
    ;

    private final String name;
}

