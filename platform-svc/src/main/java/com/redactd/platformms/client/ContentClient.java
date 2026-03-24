package com.redactd.platformms.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.redactd.platformms.bean.ContentSummary;

@FeignClient(name="CONTENTMS", url="${contentms.url:}")
public interface ContentClient {

    @GetMapping("/contents")
    List<ContentSummary> getContentsByPlatform(@RequestParam(value = "platformId", required = true) Long platformId);

    @DeleteMapping("/contents")
    void deleteContentsByPlatform(@RequestParam(value = "platformId", required = true) Long platformId);

    @GetMapping("/actuator/health")
    String healthCheck();
}






