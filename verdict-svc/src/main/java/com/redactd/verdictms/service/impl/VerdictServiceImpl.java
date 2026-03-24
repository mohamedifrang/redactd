package com.redactd.verdictms.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.redactd.verdictms.bean.Verdict;
import com.redactd.verdictms.dao.VerdictRepository;
import com.redactd.verdictms.entity.VerdictEntity;
import com.redactd.verdictms.exception.VerdictNotFoundException;
import com.redactd.verdictms.mapper.VerdictMapper;
import com.redactd.verdictms.messaging.VerdictEventPublisher;
import com.redactd.verdictms.service.PlatformClientService;
import com.redactd.verdictms.service.VerdictService;

@Service
public class VerdictServiceImpl implements VerdictService {

    private VerdictRepository VerdictRepository;
    private VerdictMapper VerdictMapper;
    private VerdictEventPublisher VerdictEventPublisher;
    private PlatformClientService PlatformClientService;
    public VerdictServiceImpl(VerdictRepository VerdictRepository, VerdictMapper VerdictMapper, VerdictEventPublisher VerdictEventPublisher, PlatformClientService PlatformClientService) {
        this.VerdictRepository = VerdictRepository;
        this.VerdictMapper = VerdictMapper;
        this.VerdictEventPublisher = VerdictEventPublisher;
        this.PlatformClientService = PlatformClientService;
    }

    @Override
    public List<Verdict> findAll(Long platformId) {
        List<VerdictEntity> verdictEntities = VerdictRepository.findByPlatformId(platformId);
        return VerdictMapper.toBeanList(verdictEntities);
    }

    @Override
    public ResponseEntity<String> addVerdict(Long platformId, Verdict Verdict) {
        Verdict.setId(null);
        Verdict.setPlatformId(platformId);
        VerdictEntity VerdictEntity = VerdictMapper.toEntity(Verdict);
        VerdictRepository.save(VerdictEntity);
        VerdictEventPublisher.publishVerdictCreatedEvent(VerdictMapper.toBean(VerdictEntity));
        return ResponseEntity.status(HttpStatus.CREATED).body("Verdict created successfully with ID: " + VerdictEntity.getId());
    }

    @Override
    public Verdict findById(Long reviewId) {
        return VerdictMapper.toBean(
            VerdictRepository.findById(reviewId)
            .orElseThrow(() -> new VerdictNotFoundException("Verdict with ID " + reviewId + " not found"))
        );
    }

    @Override
    public void updateVerdict(Long reviewId, Verdict Verdict) {
        VerdictEntity VerdictEntity = VerdictRepository.findById(reviewId)
            .orElseThrow(() -> new VerdictNotFoundException("Verdict not found with id: " + reviewId));
        String oldDecision = VerdictEntity.getDecision();
        VerdictMapper.updateEntityFromBean(Verdict, VerdictEntity);        
        VerdictRepository.save(VerdictEntity);
        VerdictEventPublisher.publishVerdictUpdatedEvent(VerdictMapper.toBean(VerdictEntity), oldDecision);
    }

    @Override
    public void deleteVerdictById(Long reviewId) {
        if(!VerdictRepository.existsById(reviewId)){
            throw new VerdictNotFoundException("Verdict with ID " + reviewId + " not found");
        }
        VerdictEventPublisher.publishVerdictDeletedEvent(findById(reviewId));
        VerdictRepository.deleteById(reviewId);
    }

    @Override
    public void deleteByPlatformId(Long platformId) {
        List<VerdictEntity> verdicts = VerdictRepository.findByPlatformId(platformId);
        if (verdicts.isEmpty()) {
            return; // no reviews to delete
        }
        VerdictRepository.deleteAll(verdicts);
    }

    @Override
    public boolean validateCompany(Long platformId){
        return PlatformClientService.getPlatformSummary(platformId).getStatusCode().is2xxSuccessful();
    }

    
}






