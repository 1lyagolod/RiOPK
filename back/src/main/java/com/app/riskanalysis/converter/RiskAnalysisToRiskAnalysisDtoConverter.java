package com.app.riskanalysis.converter;

import com.app.policy.converter.PolicyToPolicyDtoConverter;
import com.app.riskanalysis.RiskAnalysis;
import com.app.riskanalysis.RiskAnalysisDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RiskAnalysisToRiskAnalysisDtoConverter implements Converter<RiskAnalysis, RiskAnalysisDto> {

    private final PolicyToPolicyDtoConverter policyToPolicyDtoConverter;

    @Override
    public RiskAnalysisDto convert(RiskAnalysis source) {
        return new RiskAnalysisDto(
                source.getId(),

                source.getTarget(),
                source.getDeadline(),

                source.getFile(),

                source.getType().name(),
                source.getType().getName(),

                source.getStatus().name(),
                source.getStatus().getName(),

                source.getPolicies().stream().map(policyToPolicyDtoConverter::convert).collect(Collectors.toList())
        );
    }
}
