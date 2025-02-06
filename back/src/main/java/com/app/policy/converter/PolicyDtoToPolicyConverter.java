package com.app.policy.converter;

import com.app.policy.Policy;
import com.app.policy.PolicyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PolicyDtoToPolicyConverter implements Converter<PolicyDto, Policy> {
    @Override
    public Policy convert(PolicyDto source) {
        return new Policy(
                source.name(),
                source.ctr(),
                source.roi(),
                source.description()
        );
    }
}
