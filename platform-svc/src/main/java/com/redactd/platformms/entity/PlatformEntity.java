package com.redactd.platformms.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PlatformEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String description;
    private double averageModerationScore;
    private int activeContentCount;
    private double scoreSum;

    public PlatformEntity() {
    }

    public PlatformEntity(Long id, String name, String type, String description, double averageModerationScore, int activeContentCount, double scoreSum) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.averageModerationScore = averageModerationScore;
        this.activeContentCount = activeContentCount;
        this.scoreSum = scoreSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageModerationScore() {
        return averageModerationScore;
    }

    public void setAverageModerationScore(double averageModerationScore) {
        this.averageModerationScore = averageModerationScore;
    }

    public int getActiveContentCount() {
        return activeContentCount;
    }

    public void setActiveContentCount(int activeContentCount) {
        this.activeContentCount = activeContentCount;
    }

    public double getScoreSum() {
        return scoreSum;
    }

    public void setScoreSum(double scoreSum) {
        this.scoreSum = scoreSum;
    }
}






