package com.app.riskanalysis.converter;

import com.app.riskanalysis.RiskAnalysis;
import com.app.riskanalysis.RiskAnalysisDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RiskAnalysisDtoToRiskAnalysisConverter implements Converter<RiskAnalysisDto, RiskAnalysis> {
    @Override
    public RiskAnalysis convert(RiskAnalysisDto source) {
        return new RiskAnalysis(
                source.target(),
                source.deadline()
        );
    }
}
