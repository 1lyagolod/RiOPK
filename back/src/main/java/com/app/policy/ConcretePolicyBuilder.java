package com.app.policy;

import com.app.policy.Policy;

public class ConcretePolicyBuilder implements PolicyBuilder {
    private String name;
    private float ctr;
    private float roi;
    private String description;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setCtr(float ctr) {
        this.ctr = ctr;
    }

    @Override
    public void setRoi(float roi) {
        this.roi = roi;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Policy build() {
        return new Policy(name, ctr, roi, description);
    }
}
