package com.app.riskanalysis;

import com.app.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RiskAnalysisIntegrationTest {

    @Autowired
    private RiskAnalysisRepository repository;

    @Autowired
    private RiskAnalysisService service;

    private RiskAnalysis riskAnalysis1;
    private RiskAnalysis riskAnalysis2;
    private RiskAnalysis riskAnalysis3;

    @BeforeEach
    void setUp() {
        // Настроим объекты перед каждым тестом
        riskAnalysis1 = new RiskAnalysis(1L, "target1", "deadline1", "file1");
        riskAnalysis2 = new RiskAnalysis(2L, "target2", "deadline2", "file2");
        riskAnalysis3 = new RiskAnalysis(3L, "target3", "deadline3", "file3");

        repository.save(riskAnalysis1);
        repository.save(riskAnalysis2);
        repository.save(riskAnalysis3);
    }

    @AfterEach
    void tearDown() {
        // Очистим базу данных после каждого теста
        repository.deleteAll();
    }

    @Test
    void findAllSuccess() {
        List<RiskAnalysis> actualWizards = service.findAllForTest();

        assertThat(actualWizards).hasSize(3);
    }

    @Test
    void findByIdSuccess() {
        RiskAnalysis find = service.find("1");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getTarget()).isEqualTo(riskAnalysis1.getTarget());
        assertThat(find.getDeadline()).isEqualTo(riskAnalysis1.getDeadline());
        assertThat(find.getFile()).isEqualTo(riskAnalysis1.getFile());
    }

    @Test
    void findByIdNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.find("999"));
    }

    @Test
    void saveSuccess() {
        RiskAnalysis newRisk = new RiskAnalysis(null, "target4", "deadline4", "file4");

        RiskAnalysis saved = service.saveForTest(newRisk);

        assertThat(saved.getTarget()).isEqualTo(newRisk.getTarget());
        assertThat(saved.getDeadline()).isEqualTo(newRisk.getDeadline());
        assertThat(saved.getFile()).isEqualTo(newRisk.getFile());
    }

    @Test
    void updateSuccess() {
        RiskAnalysis update = new RiskAnalysis(1L, "updatedTarget", "updatedDeadline", "updatedFile");

        RiskAnalysis updated = service.updateForTest("1", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getTarget()).isEqualTo(update.getTarget());
        assertThat(updated.getDeadline()).isEqualTo(update.getDeadline());
        assertThat(updated.getFile()).isEqualTo(update.getFile());
    }

    @Test
    void updateNotFound() {
        RiskAnalysis update = new RiskAnalysis(999L, "updatedTarget", "updatedDeadline", "updatedFile");

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest("999", update));
    }

    @Test
    void deleteSuccess() {
        service.deleteForTest("1");

        assertThrows(ObjectNotFoundException.class, () -> service.find("1"));
    }

    @Test
    void deleteNotFound() {
        assertThrows(ObjectNotFoundException.class, () -> service.deleteForTest("999"));
    }
}
