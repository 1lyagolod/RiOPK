package com.app.policy;

public class PolicyDirector {
    public void constructBasicPolicy(PolicyBuilder builder) {
        builder.setName("Standard Policy");
        builder.setCtr(10.5f);
        builder.setRoi(5.0f);
        builder.setDescription("This is a standard insurance policy with basic coverage.");
    }
}
