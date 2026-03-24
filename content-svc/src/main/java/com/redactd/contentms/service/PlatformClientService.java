package com.redactd.contentms.service;

import org.springframework.http.ResponseEntity;

import com.redactd.contentms.bean.PlatformSummary;

public interface PlatformClientService {

    ResponseEntity<PlatformSummary> getPlatformSummary(Long platformId);

	ResponseEntity<PlatformSummary> platformFallback(Long platformId, Throwable throwable);

}







