package com.redactd.contentms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.JsonNode;

@FeignClient(name="PLATFORMMS", url="${platformms.url:}")
public interface PlatformClient {

    @GetMapping("/platforms/{platformId}")
    ResponseEntity<JsonNode> getPlatformSummary(@PathVariable Long platformId);
}






