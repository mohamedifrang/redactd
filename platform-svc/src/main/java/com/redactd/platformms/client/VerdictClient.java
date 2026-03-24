package com.redactd.platformms.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redactd.platformms.bean.VerdictSummary;

@FeignClient(name="VERDICTMS", url="${verdictms.url:}")
public interface VerdictClient {

    @GetMapping("/verdicts")
    List<VerdictSummary> getVerdictsByPlatform(@RequestParam(value = "platformId", required = true) Long platformId);

    @DeleteMapping("/verdicts")
    void deleteVerdictsByPlatform(@RequestParam(value = "platformId", required = true) Long platformId);

    @GetMapping("/actuator/health")
    String healthCheck();
}






