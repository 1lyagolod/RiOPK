package com.app.appUser.patterns;
import java.util.ArrayList;
import java.util.List;

interface InsurancePolicyBuilder {
    void setPolicyNumber(String policyNumber);
    void setInsuredPerson(String insuredPerson);
    void addRisk(String risk);
    void setPremiumAmount(double amount);
    InsurancePolicy build();
}
