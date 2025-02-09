package com.app.policy;

import com.app.appUser.AppUser;
import com.app.enums.PolicyStatus;
import com.app.riskanalysis.RiskAnalysis;
import com.app.policy.ConcretePolicyBuilder;
import com.app.policy.PolicyBuilder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import static com.app.util.Global.getDateNow;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Policy implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "policy_g")
    @SequenceGenerator(name = "policy_g", sequenceName = "policy_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String date = getDateNow();
    private int view = 0;
    private float ctr;
    private float roi;

    @Column(length = 5000)
    private String description;
    @Column(length = 1000)
    private String file = "";

    @Enumerated(EnumType.STRING)
    private PolicyStatus status = PolicyStatus.WAITING;

    @ManyToOne
    private RiskAnalysis riskAnalysis;
    @ManyToOne
    private AppUser owner;

    public Policy(String name, float ctr, float roi, String description) {
        this.name = name;
        this.ctr = ctr;
        this.roi = roi;
        this.description = description;
    }

    public void update(Policy update) {
        this.name = update.getName();
        this.description = update.getDescription();
    }

    public static Policy createWithBuilder(String name, float ctr, float roi, String description) {
        PolicyBuilder builder = new ConcretePolicyBuilder();
        builder.setName(name);
        builder.setCtr(ctr);
        builder.setRoi(roi);
        builder.setDescription(description);
        return builder.build();
    }
}
