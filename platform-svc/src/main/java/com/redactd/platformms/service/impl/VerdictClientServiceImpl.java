package com.redactd.platformms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.redactd.platformms.bean.VerdictSummary;
import com.redactd.platformms.client.VerdictClient;
import com.redactd.platformms.service.VerdictClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class VerdictClientServiceImpl implements VerdictClientService {

    private VerdictClient VerdictClient;
    private static final Logger logger = LoggerFactory.getLogger(VerdictClientServiceImpl.class);
    
    public VerdictClientServiceImpl(VerdictClient VerdictClient) {
        this.VerdictClient = VerdictClient;
    }

    @Override
    @RateLimiter(name = "verdictBreaker")
    @Retry(name = "verdictBreaker")
    @CircuitBreaker(name = "verdictBreaker", fallbackMethod = "getVerdictFallback")
    public List<VerdictSummary> getVerdictSummary(Long platformId) {
        return VerdictClient.getVerdictsByPlatform(platformId);
    }

    @Override
    @RateLimiter(name = "verdictBreaker")
    @Retry(name = "verdictBreaker")
    @CircuitBreaker(name = "verdictBreaker", fallbackMethod = "deleteVerdictFallback")
    public void deleteVerdictsByPlatform(Long platformId) {
        VerdictClient.deleteVerdictsByPlatform(platformId);
    }

    @Override
    public boolean isAvailable() {
        try {
            String response = VerdictClient.healthCheck(); 
            return response != null && response.contains("\"status\":\"UP\"");
        } catch (Exception e) {
            // FeignException, Timeout, Connect refused, etc.
            return false;
        }
    }

    @Override
    public List<VerdictSummary> getVerdictFallback(Long platformId, Throwable throwable) {
        return List.of();
    }

    @Override
    public void deleteVerdictFallback(Long platformId, Throwable throwable) {
        logger.error("Review service is unavailable. Unable to delete reviews for platformId: {}", platformId, throwable);
        throw new RuntimeException("Review service is currently unavailable. Please try again later.");
    }
}







