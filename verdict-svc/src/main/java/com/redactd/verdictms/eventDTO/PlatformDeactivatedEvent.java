package com.redactd.verdictms.eventDTO;

public class PlatformDeactivatedEvent {
    private Long platformId;

    public PlatformDeactivatedEvent() {
    }

    public PlatformDeactivatedEvent(Long platformId) {
        this.platformId = platformId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

}






