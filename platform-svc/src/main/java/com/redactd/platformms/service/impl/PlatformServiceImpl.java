package com.redactd.platformms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.redactd.platformms.bean.Platform;
import com.redactd.platformms.bean.ContentSummary;
import com.redactd.platformms.bean.VerdictSummary;
import com.redactd.platformms.dao.PlatformRepository;
import com.redactd.platformms.entity.PlatformEntity;
import com.redactd.platformms.eventDTO.VerdictCreatedEvent;
import com.redactd.platformms.eventDTO.VerdictDeletedEvent;
import com.redactd.platformms.eventDTO.VerdictUpdatedEvent;
import com.redactd.platformms.exception.PlatformNotFoundException;
import com.redactd.platformms.mapper.PlatformBeanMapper;
import com.redactd.platformms.messaging.PlatformEventPublisher;
import com.redactd.platformms.response.PlatformResponse;
import com.redactd.platformms.service.PlatformService;
import com.redactd.platformms.service.ContentClientService;
import com.redactd.platformms.service.VerdictClientService;

import jakarta.transaction.Transactional;

@Service
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository PlatformRepository;
    private final PlatformBeanMapper PlatformBeanMapper;
    private final PlatformEventPublisher PlatformEventPublisher;
    private final ContentClientService ContentClientService;
    private final VerdictClientService VerdictClientService;

    public PlatformServiceImpl(PlatformRepository PlatformRepository, PlatformBeanMapper PlatformBeanMapper,
            PlatformEventPublisher PlatformEventPublisher, ContentClientService ContentClientService, VerdictClientService VerdictClientService) {
        this.PlatformRepository = PlatformRepository;
        this.PlatformBeanMapper = PlatformBeanMapper;
        this.PlatformEventPublisher = PlatformEventPublisher;
        this.ContentClientService = ContentClientService;
        this.VerdictClientService = VerdictClientService;
    }

    @Override
    public List<Platform> findAll(){
        return PlatformBeanMapper.toBeanList(PlatformRepository.findAll());
    }

    @Override
    public Platform createPlatform(Platform Platform) {
        Platform.setId(null);
        PlatformEntity PlatformEntity = PlatformBeanMapper.toEntity(Platform);
        return PlatformBeanMapper.toBean(PlatformRepository.save(PlatformEntity));
    }

    @Override
    public PlatformResponse getPlatformById(Long id) {
        PlatformEntity PlatformEntity = PlatformRepository.findById(id)
            .orElseThrow(() -> new PlatformNotFoundException("Platform with ID " + id + " not found."));
        return toPlatformResponse(PlatformEntity);
    }

    @Override
    public boolean updatePlatform(Long id, Platform updatedCompany) {
        Optional<PlatformEntity> optionalPlatformEntity = PlatformRepository.findById(id);
        if(optionalPlatformEntity.isPresent()){
            PlatformEntity existingEntity = optionalPlatformEntity.get();
            PlatformBeanMapper.updateEntityFromBean(updatedCompany, existingEntity);
            PlatformRepository.save(existingEntity);
            return true;
        }
        else
            throw new PlatformNotFoundException("Platform with ID " + id + " not found.");
    }

    @Override
    public void deletePlatformById(Long id) {
        if (!PlatformRepository.existsById(id)) {
            throw new PlatformNotFoundException("Platform with ID " + id + " not found.");
        }
        PlatformRepository.deleteById(id);
        PlatformEventPublisher.publishPlatformDeactivatedEvent(id);
    }

    @Override
    public PlatformResponse toPlatformResponse(PlatformEntity entity) {
        Platform Platform = PlatformBeanMapper.toBean(entity);
        List<ContentSummary> jobResponse = ContentClientService.getContentSummary(Platform.getId());
        List<VerdictSummary> reviewResponse = VerdictClientService.getVerdictSummary(Platform.getId());
        return new PlatformResponse(Platform, jobResponse, reviewResponse);
    }

    @Override
    @Transactional
    public void updatePlatformRatingOnCreate(VerdictCreatedEvent event) {
        double score = toModerationScore(event.getDecision());
        PlatformRepository.updateRatingAtomically(event.getPlatformId(), score, 1);
    }

    @Override
    @Transactional
    public void updatePlatformRatingOnUpdate(VerdictUpdatedEvent event) {
        double deltaScore = toModerationScore(event.getNewDecision()) - toModerationScore(event.getOldDecision());
        PlatformRepository.updateRatingAtomically(event.getPlatformId(), deltaScore, 0);
    }

    @Override
    @Transactional
    public void updatePlatformRatingOnDelete(VerdictDeletedEvent event) {
        double score = toModerationScore(event.getDecision());
        PlatformRepository.updateRatingAtomically(event.getPlatformId(), -score, -1);
    }

    private double toModerationScore(String decision) {
        if (decision == null) {
            return 0.0;
        }
        return switch (decision.trim().toUpperCase()) {
            case "APPROVED" -> 1.0;
            case "ESCALATED", "DEFERRED" -> 0.5;
            case "REMOVED" -> 0.0;
            default -> 0.0;
        };
    }
    
}







