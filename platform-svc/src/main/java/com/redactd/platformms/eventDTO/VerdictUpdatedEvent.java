package com.redactd.platformms.eventDTO;

public class VerdictUpdatedEvent {
    private Long verdictId;
    private String oldDecision;
    private String newDecision;
    private Long platformId;

    public VerdictUpdatedEvent() {
    }

    public VerdictUpdatedEvent(Long verdictId, String oldDecision, String newDecision, Long platformId) {
        this.verdictId = verdictId;
        this.oldDecision = oldDecision;
        this.newDecision = newDecision;
        this.platformId = platformId;
    }

    public Long getVerdictId() {
        return verdictId;
    }

    public void setVerdictId(Long verdictId) {
        this.verdictId = verdictId;
    }

    public String getOldDecision() {
        return oldDecision;
    }

    public void setOldDecision(String oldDecision) {
        this.oldDecision = oldDecision;
    }

    public String getNewDecision() {
        return newDecision;
    }

    public void setNewDecision(String newDecision) {
        this.newDecision = newDecision;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

}






