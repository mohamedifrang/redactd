package com.redactd.platformms.service;

import java.util.List;

import com.redactd.platformms.bean.Platform;
import com.redactd.platformms.entity.PlatformEntity;
import com.redactd.platformms.eventDTO.VerdictCreatedEvent;
import com.redactd.platformms.eventDTO.VerdictDeletedEvent;
import com.redactd.platformms.eventDTO.VerdictUpdatedEvent;
import com.redactd.platformms.response.PlatformResponse;

public interface PlatformService {

    List<Platform> findAll();
    Platform createPlatform(Platform Platform);
    PlatformResponse getPlatformById(Long id);
    boolean updatePlatform(Long id, Platform updatedCompany);
    PlatformResponse toPlatformResponse(PlatformEntity entity);
    void deletePlatformById(Long id);
    void updatePlatformRatingOnCreate(VerdictCreatedEvent event);
    void updatePlatformRatingOnUpdate(VerdictUpdatedEvent event);
    void updatePlatformRatingOnDelete(VerdictDeletedEvent event);
}







