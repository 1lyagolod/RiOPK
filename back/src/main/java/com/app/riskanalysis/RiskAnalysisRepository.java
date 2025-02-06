package com.app.riskanalysis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskAnalysisRepository extends JpaRepository<RiskAnalysis, Long> {
}
