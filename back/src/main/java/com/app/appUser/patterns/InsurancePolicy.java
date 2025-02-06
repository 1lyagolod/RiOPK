package com.app.appUser.patterns;
import java.util.ArrayList;
import java.util.List;

class InsurancePolicy {
    private String policyNumber;
    private String insuredPerson;
    private List<String> risks;
    private double premiumAmount;

    public InsurancePolicy(String policyNumber, String insuredPerson, List<String> risks, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.insuredPerson = insuredPerson;
        this.risks = risks;
        this.premiumAmount = premiumAmount;
    }

    public void showPolicyDetails() {
        System.out.println("Страховой полис #" + policyNumber);
        System.out.println("Застрахованное лицо: " + insuredPerson);
        System.out.println("Риски: " + String.join(", ", risks));
        System.out.println("Страховая премия: " + premiumAmount + " руб.");
    }
}
