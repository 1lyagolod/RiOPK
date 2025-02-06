package com.app.appUser.patterns;

class InsurancePolicyDirector {
    public void constructBasicPolicy(InsurancePolicyBuilder builder) {
        builder.setPolicyNumber("P-12345");
        builder.setInsuredPerson("Иван Иванов");
        builder.addRisk("Пожар");
        builder.addRisk("Затопление");
        builder.setPremiumAmount(10000);
    }
}
