package com.redactd.platformms.eventDTO;

public class VerdictCreatedEvent {
    private Long verdictId;
    private String decision;
    private Long platformId;
    private Long contentId;

    public VerdictCreatedEvent() {
    }

    public VerdictCreatedEvent(Long verdictId, String decision, Long platformId, Long contentId) {
        this.verdictId = verdictId;
        this.decision = decision;
        this.platformId = platformId;
        this.contentId = contentId;
    }

    public Long getVerdictId() {
        return verdictId;
    }

    public void setVerdictId(Long verdictId) {
        this.verdictId = verdictId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
}






