package com.app.appUser.patterns;
import java.util.ArrayList;
import java.util.List;

class ConcreteInsurancePolicyBuilder implements InsurancePolicyBuilder {
    private String policyNumber;
    private String insuredPerson;
    private List<String> risks = new ArrayList<>();
    private double premiumAmount;

    @Override
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    @Override
    public void addRisk(String risk) {
        risks.add(risk);
    }

    @Override
    public void setPremiumAmount(double amount) {
        this.premiumAmount = amount;
    }

    @Override
    public InsurancePolicy build() {
        return new InsurancePolicy(policyNumber, insuredPerson, risks, premiumAmount);
    }
}
