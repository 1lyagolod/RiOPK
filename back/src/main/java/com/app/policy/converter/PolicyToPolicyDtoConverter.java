package com.app.policy.converter;

import com.app.policy.Policy;
import com.app.policy.PolicyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PolicyToPolicyDtoConverter implements Converter<Policy, PolicyDto> {
    @Override
    public PolicyDto convert(Policy source) {
        return new PolicyDto(
                source.getId(),

                source.getName(),
                source.getDate(),

                source.getView(),

                source.getCtr(),
                source.getRoi(),

                source.getDescription(),

                source.getFile(),

                source.getStatus().name(),
                source.getStatus().getName(),

                source.getRiskAnalysis().getId()
        );
    }
}
