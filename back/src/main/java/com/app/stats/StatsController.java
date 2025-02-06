package com.app.stats;

import com.app.policy.Policy;
import com.app.policy.PolicyService;
import com.app.system.Result;
import com.app.system.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.app.util.Global.ADMIN;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@Secured({ADMIN})
public class StatsController {

    private final PolicyService policyService;

    @GetMapping("/view")
    public Result view() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        List<Policy> policies = policyService.findAll(Sort.by(Sort.Direction.DESC, "view"));

        for (int i = 0; i < policies.size(); i++) {
            if (i == 5) break;
            names.add(policies.get(i).getName());
            values.add(policies.get(i).getView());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Stats View",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/c")
    public Result ctr() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Float> values = new ArrayList<>();

        List<Policy> policies = policyService.findAll(Sort.by(Sort.Direction.DESC, "ctr"));

        for (int i = 0; i < policies.size(); i++) {
            if (i == 5) break;
            names.add(policies.get(i).getName());
            values.add(policies.get(i).getCtr());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Stats CTR",
                Collections.unmodifiableMap(res)
        );
    }

    @GetMapping("/roi")
    public Result roi() {
        Map<String, List<?>> res = new HashMap<>();

        List<String> names = new ArrayList<>();
        List<Float> values = new ArrayList<>();

        List<Policy> policies = policyService.findAll(Sort.by(Sort.Direction.DESC, "roi"));

        for (int i = 0; i < policies.size(); i++) {
            if (i == 5) break;
            names.add(policies.get(i).getName());
            values.add(policies.get(i).getRoi());
        }

        res.put("names", names);
        res.put("values", values);

        return new Result(
                true,
                StatusCode.SUCCESS,
                "Success Stats ROI",
                Collections.unmodifiableMap(res)
        );
    }

}
