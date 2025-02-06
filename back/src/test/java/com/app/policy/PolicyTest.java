package com.app.policy;

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
public class PolicyTest {

    @Mock
    private PolicyRepository repository;
    @InjectMocks
    private PolicyService service;

    List<Policy> policies = new ArrayList<>();

    @BeforeEach
    void setUp() {
        policies.add(new Policy(1L, "name1", "date1", 9, 9.99f, 9.99f, "description1", "file1"));
        policies.add(new Policy(2L, "name2", "date2", 19, 19.99f, 19.99f, "description2", "file2"));
        policies.add(new Policy(3L, "name3", "date3", 19, 19.99f, 19.99f, "description3", "file3"));
    }

    @AfterEach
    void tearDown() {
        policies.clear();
    }

    @Test
    void findAllSuccess() {
        given(repository.findAll()).willReturn(policies);

        List<Policy> actualWizards = service.findAllForTest();

        assertThat(actualWizards.size()).isEqualTo(policies.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findByIdSuccess() {
        Policy policy = policies.get(0);

        given(repository.findById(1L)).willReturn(Optional.of(policy));

        Policy find = service.findForTest(1 + "");

        assertThat(find.getId()).isEqualTo(1);
        assertThat(find.getName()).isEqualTo(policy.getName());
        assertThat(find.getDate()).isEqualTo(policy.getDate());
        assertThat(find.getView()).isEqualTo(policy.getView());
        assertThat(find.getCtr()).isEqualTo(policy.getCtr());
        assertThat(find.getRoi()).isEqualTo(policy.getRoi());
        assertThat(find.getDescription()).isEqualTo(policy.getDescription());
        assertThat(find.getFile()).isEqualTo(policy.getFile());

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        given(repository.findById(Mockito.any(Long.class))).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.findForTest(1 + ""));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void saveSuccess() {
        Policy save = policies.get(0);

        given(repository.save(save)).willReturn(save);

        Policy saved = service.saveForTest(save);

        assertThat(saved.getName()).isEqualTo(save.getName());
        assertThat(saved.getDate()).isEqualTo(save.getDate());
        assertThat(saved.getView()).isEqualTo(save.getView());
        assertThat(saved.getCtr()).isEqualTo(save.getCtr());
        assertThat(saved.getRoi()).isEqualTo(save.getRoi());
        assertThat(saved.getDescription()).isEqualTo(save.getDescription());
        assertThat(saved.getFile()).isEqualTo(save.getFile());

        verify(repository, times(1)).save(save);
    }

    @Test
    void updateSuccess() {
        Policy old = policies.get(0);
        Policy update = policies.get(1);

        given(repository.findById(1L)).willReturn(Optional.of(old));
        given(repository.save(old)).willReturn(old);

        Policy updated = service.updateForTest(1 + "", update);

        assertThat(updated.getId()).isEqualTo(1);
        assertThat(updated.getName()).isEqualTo(update.getName());
        assertThat(updated.getDate()).isEqualTo(update.getDate());
        assertThat(updated.getView()).isEqualTo(update.getView());
        assertThat(updated.getCtr()).isEqualTo(update.getCtr());
        assertThat(updated.getRoi()).isEqualTo(update.getRoi());
        assertThat(updated.getDescription()).isEqualTo(update.getDescription());
        assertThat(updated.getFile()).isEqualTo(update.getFile());

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(old);
    }

    @Test
    void updateNotFound() {
        Policy update = policies.get(1);

        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.updateForTest(1 + "", update));

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deleteSuccess() {
        Policy wizard = policies.get(0);

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
