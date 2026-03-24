package com.redactd.platformms.response;

import java.util.List;

import com.redactd.platformms.bean.Platform;
import com.redactd.platformms.bean.ContentSummary;
import com.redactd.platformms.bean.VerdictSummary;

public class PlatformResponse {
    private Platform Platform;
    private List<ContentSummary> ContentSummary;
    private List<VerdictSummary> VerdictSummary;
    public PlatformResponse(Platform Platform, List<ContentSummary> ContentSummary, List<VerdictSummary> VerdictSummary) {
        this.Platform = Platform;
        this.ContentSummary = ContentSummary;
        this.VerdictSummary = VerdictSummary;
    }
    public Platform getCompany() {
        return Platform;
    }
    public void setCompany(Platform Platform) {
        this.Platform = Platform;
    }
    public List<ContentSummary> getContentSummary() {
        return ContentSummary;
    }
    public void setJobSummary(List<ContentSummary> ContentSummary) {
        this.ContentSummary = ContentSummary;
    }
    public List<VerdictSummary> getVerdictSummary() {
        return VerdictSummary;
    }
    public void setReviewSummary(List<VerdictSummary> VerdictSummary) {
        this.VerdictSummary = VerdictSummary;
    }
    
}







