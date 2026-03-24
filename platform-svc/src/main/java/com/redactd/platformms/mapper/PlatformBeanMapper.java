package com.redactd.platformms.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.redactd.platformms.bean.Platform;
import com.redactd.platformms.entity.PlatformEntity;

@Component
public class PlatformBeanMapper {

    public Platform toBean(PlatformEntity platformEntity) {
        if (platformEntity == null) {
            return null;
        }

        Platform platform = new Platform();
        platform.setId(platformEntity.getId());
        platform.setName(platformEntity.getName());
        platform.setDescription(platformEntity.getDescription());
        platform.setAverageRating(platformEntity.getAverageModerationScore());
        platform.setReviewCount(platformEntity.getActiveContentCount());
        return platform;
    }

    public PlatformEntity toEntity(Platform platform) {
        if (platform == null) {
            return null;
        }
        PlatformEntity platformEntity = new PlatformEntity();
        platformEntity.setId(platform.getId());
        platformEntity.setName(platform.getName());
        platformEntity.setDescription(platform.getDescription());
        platformEntity.setAverageModerationScore(platform.getAverageRating());
        platformEntity.setActiveContentCount(platform.getReviewCount());
        return platformEntity;
    }

    public List<Platform> toBeanList(List<PlatformEntity> platformEntities) {
        List<Platform> platforms = new ArrayList<>();
        if (platformEntities == null) {
            return platforms;
        }
        for (PlatformEntity platformEntity : platformEntities) {
            platforms.add(toBean(platformEntity));
        }
        return platforms;
    }

    public void updateEntityFromBean(Platform platform, PlatformEntity platformEntity) {
        if (platform == null || platformEntity == null) {
            return;
        }
        if (platform.getName() != null) {
            platformEntity.setName(platform.getName());
        }
        if (platform.getDescription() != null) {
            platformEntity.setDescription(platform.getDescription());
        }
        platformEntity.setAverageModerationScore(platform.getAverageRating());
        platformEntity.setActiveContentCount(platform.getReviewCount());
    }
}
