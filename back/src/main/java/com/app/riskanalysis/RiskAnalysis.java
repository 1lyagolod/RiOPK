package com.app.riskanalysis;

import com.app.appUser.AppUser;
import com.app.policy.Policy;
import com.app.enums.RiskAnalysisStatus;
import com.app.enums.RiskAnalysisType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class RiskAnalysis implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "riskanalysis_g")
    @SequenceGenerator(name = "riskanalysis_g", sequenceName = "riskanalysis_seq", allocationSize = 1)
    private Long id;

    private String target;
    private String deadline;

    @Column(length = 1000)
    private String file = "";

    @Enumerated(EnumType.STRING)
    private RiskAnalysisType type;
    @Enumerated(EnumType.STRING)
    private RiskAnalysisStatus status = RiskAnalysisStatus.WAITING;

    @ManyToOne
    private AppUser owner;
    @ManyToOne
    private AppUser marketing;
    @OneToMany(mappedBy = "riskanalysis", cascade = CascadeType.ALL)
    private List<Policy> policies = new ArrayList<>();

    public RiskAnalysis(String target, String deadline) {
        this.target = target;
        this.deadline = deadline;
    }

    public RiskAnalysis(Long id, String target, String deadline, String file) {
        this.id = id;
        this.target = target;
        this.deadline = deadline;
        this.file = file;
    }

    public void updateForTest(RiskAnalysis update) {
        this.target = update.getTarget();
        this.deadline = update.getDeadline();
        this.file = update.getFile();
    }

    public List<Policy> getPolicies() {
        policies.sort(Comparator.comparing(Policy::getId));
        Collections.reverse(policies);
        return policies;
    }
}