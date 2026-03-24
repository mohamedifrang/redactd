package com.redactd.verdictms.eventDTO;

public class VerdictDeletedEvent {
    private Long verdictId;
    private Long platformId;
    private String decision;

    public VerdictDeletedEvent() {
    }

    public VerdictDeletedEvent(Long verdictId, Long platformId, String decision) {
        this.verdictId = verdictId;
        this.platformId = platformId;
        this.decision = decision;
    }

    public Long getVerdictId() {
        return verdictId;
    }

    public void setVerdictId(Long verdictId) {
        this.verdictId = verdictId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}






