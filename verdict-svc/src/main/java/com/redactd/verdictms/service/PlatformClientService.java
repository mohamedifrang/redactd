package com.redactd.verdictms.service;

import org.springframework.http.ResponseEntity;

import com.redactd.verdictms.bean.PlatformSummary;

public interface PlatformClientService {

    ResponseEntity<PlatformSummary> getPlatformSummary(Long platformId);

    ResponseEntity<PlatformSummary> platformFallback(Long platformId, Throwable throwable);

}







