package com.app.riskanalysis;

import com.app.riskanalysis.converter.RiskAnalysisDtoToRiskAnalysisConverter;
import com.app.riskanalysis.converter.RiskAnalysisToRiskAnalysisDtoConverter;
import com.app.system.Result;
import com.app.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

import static com.app.util.Global.CLIENT;
import static com.app.util.Global.POLICY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderings")
public class RiskAnalysisController {

    private final RiskAnalysisService service;
    private final RiskAnalysisToRiskAnalysisDtoConverter toDtoConverter;
    private final RiskAnalysisDtoToRiskAnalysisConverter toConverter;

    @GetMapping
    @Secured({CLIENT, POLICY})
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/history")
    @Secured({POLICY})
    public Result history() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success History",
                service.history().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @PostMapping
    @Secured({CLIENT})
    public Result save(@Valid @RequestBody RiskAnalysisDto saveDto, @RequestParam String type) {
        RiskAnalysis save = toConverter.convert(saveDto);
        RiskAnalysis saved = service.save(save, type);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(saved)
        );
    }

    @PatchMapping("/{id}/file")
    @Secured({CLIENT})
    public Result updateFile(@PathVariable String id, @RequestParam MultipartFile files) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update File",
                toDtoConverter.convert(service.updateFile(id, files))
        );
    }

    @GetMapping("/{id}/approved")
    @Secured({POLICY})
    public Result approved(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Approved",
                toDtoConverter.convert(service.approved(id))
        );
    }

    @GetMapping("/{id}/not")
    @Secured({POLICY})
    public Result not(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Not",
                toDtoConverter.convert(service.not(id))
        );
    }

}
