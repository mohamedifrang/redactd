package com.redactd.contentms.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redactd.contentms.bean.PlatformSummary;
import com.redactd.contentms.client.PlatformClient;
import com.redactd.contentms.service.PlatformClientService;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class PlatformClientServiceImpl implements PlatformClientService {

    private PlatformClient PlatformClient;
    private ObjectMapper objectMapper;

    public PlatformClientServiceImpl(PlatformClient PlatformClient, ObjectMapper objectMapper) {
        this.PlatformClient = PlatformClient;
        this.objectMapper = objectMapper;
    }

    @Override
    @RateLimiter(name = "platformBreaker")
    @Retry(name = "platformBreaker")
    @CircuitBreaker(name = "platformBreaker", fallbackMethod = "platformFallback")
    public ResponseEntity<PlatformSummary> getPlatformSummary(Long platformId) {
        try {
            ResponseEntity<JsonNode> response = PlatformClient.getPlatformSummary(platformId);
            JsonNode node = response.getBody();
            PlatformSummary PlatformSummary = objectMapper.treeToValue(node.get("company"), PlatformSummary.class);
            return new ResponseEntity<>(PlatformSummary, response.getStatusCode());
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Error processing Json: " + ex.getMessage(), ex);
        }
    }

    @Override
    public ResponseEntity<PlatformSummary> platformFallback(Long platformId, Throwable throwable) {
        if(throwable instanceof IllegalArgumentException) {
            throw (IllegalArgumentException) throwable;
        }
        else if(throwable instanceof FeignException.NotFound) {
            throw (FeignException.NotFound) throwable;
        }
        PlatformSummary fallbackSummary = new PlatformSummary();
        fallbackSummary.setId(platformId);
        fallbackSummary.setName("Unknown");
        fallbackSummary.setDescription("Platform service is currently unavailable.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(fallbackSummary);
    }
}







