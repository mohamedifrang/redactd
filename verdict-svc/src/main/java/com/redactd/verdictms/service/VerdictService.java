package com.redactd.verdictms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.redactd.verdictms.bean.Verdict;

public interface VerdictService {

    List<Verdict> findAll(Long platformId);
    ResponseEntity<String> addVerdict(Long platformId, Verdict Verdict);
    Verdict findById(Long reviewId);
    void updateVerdict(Long reviewId, Verdict Verdict);
    void deleteVerdictById(Long reviewId);
    void deleteByPlatformId(Long platformId);
    boolean validateCompany(Long platformId);
}






