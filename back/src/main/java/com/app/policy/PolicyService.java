package com.app.policy;

import com.app.appUser.UserService;
import com.app.enums.PolicyStatus;
import com.app.policy.patterns.PolicyBuilder;
import com.app.policy.patterns.PolicyDirector;
import com.app.riskanalysis.RiskAnalysisService;
import com.app.system.exception.BadRequestException;
import com.app.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.app.util.Global.saveFile;

@Service
@RequiredArgsConstructor
public class PolicyService {

    private final PolicyRepository repository;
    private final RiskAnalysisService riskAnalysisService;
    private final UserService userService;

    public List<Policy> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public List<Policy> my() {
        return userService.getCurrentUser().getPolicies();
    }

    public Policy find(String id) {
        Policy policy = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new ObjectNotFoundException("Не найден контент по ИД: " + id));
        policy.setView(policy.getView() + 1);
        return repository.save(policy);
    }

    public Policy approved(String id) {
        Policy policy = find(id);
        policy.setStatus(PolicyStatus.APPROVED);
        return repository.save(policy);
    }

    public Policy revision(String id) {
        Policy policy = find(id);
        policy.setStatus(PolicyStatus.REVISION);
        return repository.save(policy);
    }

    public Policy waiting(String id) {
        Policy policy = find(id);
        policy.setStatus(PolicyStatus.WAITING);
        return repository.save(policy);
    }

    public Policy save(Policy save, String orderingId) {
        save.setRiskAnalysis(riskAnalysisService.find(orderingId));
        save.setOwner(userService.getCurrentUser());
        return repository.save(save);
    }

    public Policy createPolicyWithBuilder(String name, float ctr, float roi, String description) {
        PolicyBuilder builder = new PolicyBuilder();
        PolicyDirector director = new PolicyDirector();
        director.constructBasicPolicy(builder);
        builder.setName(name);
        builder.setCtr(ctr);
        builder.setRoi(roi);
        builder.setDescription(description);
        return builder.build();
    }

    public Policy update(String id, Policy update, String orderingId) {
        Policy old = find(id);
        old.update(update);
        old.setRiskAnalysis(riskAnalysisService.find(orderingId));
        return repository.save(old);
    }

    public Policy updateFile(String id, MultipartFile file) {
        Policy policy = find(id);
        try {
            policy.setFile(saveFile(file, "policy"));
        } catch (IOException e) {
            if (policy.getFile().isEmpty()) repository.deleteById(policy.getId());
            throw new BadRequestException("Некорректный файл");
        }
        return repository.save(policy);
    }

    public void delete(String id) {
        repository.deleteById(find(id).getId());
    }
}
