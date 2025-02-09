package com.app.policy;

import com.app.policy.Policy;

public interface PolicyBuilder {
    void setName(String name);
    void setCtr(float ctr);
    void setRoi(float roi);
    void setDescription(String description);
    Policy build();
}