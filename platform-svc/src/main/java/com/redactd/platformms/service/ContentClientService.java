package com.redactd.platformms.service;

import java.util.List;

import com.redactd.platformms.bean.ContentSummary;

public interface ContentClientService {

    List<ContentSummary> getContentSummary(Long platformId);

    void deleteContentsByPlatform(Long id);

    boolean isAvailable();

    List<ContentSummary> getContentFallback(Long platformId, Throwable throwable);

    void deleteContentFallback(Long platformId, Throwable throwable);

}







