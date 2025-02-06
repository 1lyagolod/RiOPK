package com.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RiskAnalysisType {
    RISK_ANALYSIS_TYPE("Тип"),
    RISK_ANALYSIS_TYPE1("Тип 1"),
    RISK_ANALYSIS_TYPE2("Тип 2"),
    ;

    private final String name;
}

