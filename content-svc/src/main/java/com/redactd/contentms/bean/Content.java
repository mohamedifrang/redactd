package com.redactd.contentms.bean;

public class Content {
    private Long id;
    private Long platformId;
    private String title;
    private String body;
    private String authorId;
    private String flagReason;
    private String status;
    private String severity;
    
    public Content() {
    }
    public Content(Long id, Long platformId, String title, String body, String authorId, String flagReason,
            String status, String severity) {
        this.id = id;
        this.platformId = platformId;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.flagReason = flagReason;
        this.status = status;
        this.severity = severity;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getPlatformId() {
        return platformId;
    }
    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getFlagReason() {
        return flagReason;
    }
    public void setFlagReason(String flagReason) {
        this.flagReason = flagReason;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    
}






