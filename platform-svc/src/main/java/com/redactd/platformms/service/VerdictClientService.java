package com.redactd.platformms.service;

import java.util.List;

import com.redactd.platformms.bean.VerdictSummary;

public interface VerdictClientService {

    List<VerdictSummary> getVerdictSummary(Long platformId);

    void deleteVerdictsByPlatform(Long id);

    boolean isAvailable();

    List<VerdictSummary> getVerdictFallback(Long platformId, Throwable throwable);

    void deleteVerdictFallback(Long platformId, Throwable throwable);

}







