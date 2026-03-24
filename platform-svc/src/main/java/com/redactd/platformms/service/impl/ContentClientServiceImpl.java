package com.redactd.platformms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.redactd.platformms.bean.ContentSummary;
import com.redactd.platformms.client.ContentClient;
import com.redactd.platformms.service.ContentClientService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ContentClientServiceImpl implements ContentClientService {

    private ContentClient ContentClient;
    private static final Logger logger = LoggerFactory.getLogger(ContentClientServiceImpl.class);

    public ContentClientServiceImpl(ContentClient ContentClient) {
        this.ContentClient = ContentClient;
    }

    @Override
    @RateLimiter(name = "contentBreaker")
    @Retry(name = "contentBreaker")
    @CircuitBreaker(name = "contentBreaker", fallbackMethod = "getContentFallback")
    public List<ContentSummary> getContentSummary(Long platformId) {
        return ContentClient.getContentsByPlatform(platformId);
    }

    @Override
    @RateLimiter(name = "contentBreaker")
    @Retry(name = "contentBreaker")
    @CircuitBreaker(name = "contentBreaker", fallbackMethod = "deleteContentFallback")
    public void deleteContentsByPlatform(Long platformId) {
        ContentClient.deleteContentsByPlatform(platformId);
    }

    @Override
    public boolean isAvailable() {
        try {
            String response = ContentClient.healthCheck(); 
            return response != null && response.contains("\"status\":\"UP\"");
        } catch (Exception e) {
            // FeignException, Timeout, Connect refused, etc.
            return false;
        }
    }

    @Override
    public List<ContentSummary> getContentFallback(Long platformId, Throwable throwable) {
        logger.error("Job service is unavailable. Falling back to empty job list for platformId: {}", platformId, throwable);
        return List.of();
    }

    @Override
    public void deleteContentFallback(Long platformId, Throwable throwable) {
        logger.error("Job service is unavailable. Unable to delete jobs for platformId: {}", platformId, throwable);
        throw new RuntimeException("Job service is currently unavailable. Please try again later.");
    }

}







