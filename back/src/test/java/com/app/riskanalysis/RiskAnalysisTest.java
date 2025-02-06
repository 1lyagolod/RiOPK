package com.app.riskanalysis;

import com.app.system.exception.ObjectNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiskAnalysisTest {

    @Mock
    private RiskAnalysisRepository repository;
    @InjectMocks
    private RiskAnalysisService service;

    List<RiskAnalysis> riskAnalyses = new ArrayList<>();

    @BeforeEach
    void setUp() {
        riskAnalyses.add(new RiskAnalysis(1L, "target1", "deadline1", "file1"));
        riskAnalyses.add(new RiskAnalysis(2L, "target2", "deadline2", "file2"));
        riskAnalyses.add(new RiskAnalysis(3L, "target3", "deadline3", "file3"));
    }

    @AfterEach
    void tearDown() {
        riskAnalyses.clear();
    }

    @Test
    void findAllSuccess() {
        given(repository.findAll()).willReturn(riskAnalyses);

        List<RiskAnalysis> actualWizards = service.findAllForTest();

        assertThat(actualWizards.size()).isEqualTo(riskAnalyses.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findByIdSuccess() {
        RiskAnalysis riskAnalysis = riskAnalyses.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(riskAnalysis));

        RiskAnalysis find = service.find(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getTarget()).isEqualTo(riskAnalysis.getTarget());
        assertThat(find.getDeadline()).isEqualTo(riskAnalysis.getDeadline());
        assertThat(find.getFile()).isEqualTo(riskAnalysis.getFile());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        given(repository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.find(1 + ""));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void saveSuccess() {
        RiskAnalysis save = riskAnalyses.get(0);

        given(repository.save(save)).willReturn(save);

        RiskAnalysis saved = service.saveForTest(save);

        assertThat(saved.getTarget()).isEqualTo(save.getTarget());
        assertThat(saved.getDeadline()).isEqualTo(save.getDeadline());
        assertThat(saved.getFile()).isEqualTo(save.getFile());

        verify(repository, times(1)).save(save);
    }

    @Test
    void updateSuccess() {
        RiskAnalysis old = riskAnalyses.get(0);
        RiskAnalysis update = riskAnalyses.get(1);

        given(repository.findById(1L)).willReturn(Optional.of(old));
        given(repository.save(old)).willReturn(old);

        RiskAnalysis updated = service.updateForTest(1 + "", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getTarget()).isEqualTo(update.getTarget());
        assertThat(updated.getDeadline()).isEqualTo(update.getDeadline());
        assertThat(updated.getFile()).isEqualTo(update.getFile());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(old);
    }

    @Test
    void updateNotFound() {
        RiskAnalysis update = riskAnalyses.get(1);

        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest(1 + "", update));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteSuccess() {
        RiskAnalysis wizard = riskAnalyses.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(wizard));
        doNothing().when(repository).deleteById(1L);

        service.deleteForTest(1 + "");

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deleteNotFound() {
        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.deleteForTest(1 + ""));

        verify(repository, times(1)).findById(1L);
    }


}
