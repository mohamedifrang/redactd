package com.redactd.verdictms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class VerdictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long platformId;
    private Long contentId;
    private String decision;
    private String moderatorNote;
    private String reasoning;
    private LocalDateTime createdAt;
    
    public VerdictEntity() {
    }

    public VerdictEntity(Long id, Long platformId, Long contentId, String decision, String moderatorNote, String reasoning, LocalDateTime createdAt) {
        this.id = id;
        this.platformId = platformId;
        this.contentId = contentId;
        this.decision = decision;
        this.moderatorNote = moderatorNote;
        this.reasoning = reasoning;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getModeratorNote() {
        return moderatorNote;
    }

    public void setModeratorNote(String moderatorNote) {
        this.moderatorNote = moderatorNote;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}






