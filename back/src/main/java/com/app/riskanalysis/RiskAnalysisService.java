package com.app.riskanalysis;

import com.app.appUser.AppUser;
import com.app.appUser.UserService;
import com.app.enums.RiskAnalysisStatus;
import com.app.enums.RiskAnalysisType;
import com.app.enums.Role;
import com.app.system.exception.BadRequestException;
import com.app.system.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static com.app.util.Global.saveFile;

@Service
@RequiredArgsConstructor
public class RiskAnalysisService {

    private final RiskAnalysisRepository repository;
    private final UserService userService;

    public List<RiskAnalysis> findAll() {
        List<RiskAnalysis> riskAnalyses;

        AppUser user = userService.getCurrentUser();

        if (Objects.requireNonNull(user.getRole()) == Role.CLIENT) {
            riskAnalyses = user.getOrderingsOwner();
        } else {
            riskAnalyses = new ArrayList<>();
        }

        riskAnalyses.sort(Comparator.comparing(RiskAnalysis::getId));
        Collections.reverse(riskAnalyses);

        return riskAnalyses;
    }

    public List<RiskAnalysis> findAllForTest() {
        return repository.findAll();
    }

    public List<RiskAnalysis> history() {
        return userService.getCurrentUser().getOrderingsMarketing();
    }

    public RiskAnalysis find(String id) {
        return repository.findById(Long.parseLong(id)).orElseThrow(() -> new ObjectNotFoundException("Не найдена заявка по ИД: " + id));
    }

    public RiskAnalysis save(RiskAnalysis save, String type) {
        try {
            save.setType(RiskAnalysisType.valueOf(type));
        } catch (Exception e) {
            throw new BadRequestException("Некорректный тип");
        }
        save.setOwner(userService.getCurrentUser());
        return repository.save(save);
    }

    public RiskAnalysis updateFile(String id, MultipartFile file) {
        RiskAnalysis riskAnalysis = find(id);
        try {
            riskAnalysis.setFile(saveFile(file, "riskAnalysis"));
        } catch (IOException e) {
            if (riskAnalysis.getFile().isEmpty()) repository.deleteById(riskAnalysis.getId());
            throw new BadRequestException("Некорректный файл");
        }
        return repository.save(riskAnalysis);
    }

    public RiskAnalysis saveForTest(RiskAnalysis save) {
        return repository.save(save);
    }

    public RiskAnalysis updateForTest(String id, RiskAnalysis update) {
        RiskAnalysis old = find(id);
        old.updateForTest(update);
        return repository.save(old);
    }

    public void deleteForTest(String id) {
        repository.deleteById(find(id).getId());
    }

    public RiskAnalysis approved(String id) {
        RiskAnalysis riskAnalysis = find(id);
        riskAnalysis.setStatus(RiskAnalysisStatus.APPROVED);
        riskAnalysis.setMarketing(userService.getCurrentUser());
        return repository.save(riskAnalysis);
    }

    public RiskAnalysis not(String id) {
        RiskAnalysis riskAnalysis = find(id);
        riskAnalysis.setStatus(RiskAnalysisStatus.NOT);
        riskAnalysis.setMarketing(userService.getCurrentUser());
        return repository.save(riskAnalysis);
    }

}
