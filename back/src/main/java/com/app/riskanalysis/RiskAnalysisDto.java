package com.app.riskanalysis;

import com.app.policy.PolicyDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record RiskAnalysisDto(
        Long id,

        @Size(min = 1, max = 255, message = "target is required length 1-255")
        @NotEmpty(message = "target is required")
        String target,
        @Size(min = 1, max = 255, message = "deadline is required length 1-255")
        @NotEmpty(message = "deadline is required")
        String deadline,

        String file,

        String type,
        String typeName,

        String status,
        String statusName,

        List<PolicyDto> contents
) {
}
