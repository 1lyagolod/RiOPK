package com.app.policy;

import com.app.policy.converter.PolicyDtoToPolicyConverter;
import com.app.policy.converter.PolicyToPolicyDtoConverter;
import com.app.system.Result;
import com.app.system.StatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

import static com.app.util.Global.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class PolicyController {

    private final PolicyService service;
    private final PolicyToPolicyDtoConverter toDtoConverter;
    private final PolicyDtoToPolicyConverter toConverter;

    @GetMapping
    public Result findAll() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find All",
                service.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/my")
    @Secured({POLICY})
    public Result my() {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find My",
                service.my().stream().map(toDtoConverter::convert).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    @Secured({ADMIN, ANALYST, CLIENT})
    public Result find(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Find",
                toDtoConverter.convert(service.find(id))
        );
    }

    @GetMapping("/{id}/approved")
    @Secured({ADMIN})
    public Result approved(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Approved",
                toDtoConverter.convert(service.approved(id))
        );
    }

    @GetMapping("/{id}/revision")
    @Secured({ADMIN})
    public Result revision(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Revision",
                toDtoConverter.convert(service.revision(id))
        );
    }

    @GetMapping("/{id}/waiting")
    @Secured({ADMIN})
    public Result waiting(@PathVariable String id) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Waiting",
                toDtoConverter.convert(service.waiting(id))
        );
    }

    @PostMapping
    @Secured({POLICY})
    public Result save(@Valid @RequestBody PolicyDto saveDto, @RequestParam String orderingId) {
        Policy save = toConverter.convert(saveDto);
        Policy saved = service.save(save, orderingId);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Save",
                toDtoConverter.convert(saved)
        );
    }

    @PutMapping("/{id}")
    @Secured({POLICY})
    public Result update(@PathVariable String id, @Valid @RequestBody PolicyDto updateDto, @RequestParam String orderingId) {
        Policy update = toConverter.convert(updateDto);
        Policy updated = service.update(id, update, orderingId);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update",
                toDtoConverter.convert(updated)
        );
    }

    @PatchMapping("/{id}/file")
    @Secured({POLICY})
    public Result updateFile(@PathVariable String id, @RequestParam MultipartFile files) {
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Update File",
                toDtoConverter.convert(service.updateFile(id, files))
        );
    }

    @DeleteMapping("/{id}")
    @Secured({POLICY})
    public Result delete(@PathVariable String id) {
        service.delete(id);
        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Delete"
        );
    }
}
