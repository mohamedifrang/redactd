package com.redactd.contentms.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.redactd.contentms.bean.PlatformSummary;
import com.redactd.contentms.bean.Content;
import com.redactd.contentms.dao.ContentRepository;
import com.redactd.contentms.entity.ContentEntity;
import com.redactd.contentms.exception.ContentNotFoundException;
import com.redactd.contentms.mapper.ContentMapper;
import com.redactd.contentms.response.ContentResponse;
import com.redactd.contentms.service.PlatformClientService;
import com.redactd.contentms.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

    private ContentRepository ContentRepository;
    private ContentMapper ContentMapper;
    private PlatformClientService PlatformClientService;
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    public ContentServiceImpl(ContentRepository ContentRepository, ContentMapper ContentMapper, PlatformClientService PlatformClientService) {
        this.ContentRepository = ContentRepository;
        this.ContentMapper = ContentMapper;
        this.PlatformClientService = PlatformClientService;
    }

    @Override
    public List<ContentResponse> findAll() {
        List<ContentEntity> contentEntities = ContentRepository.findAll();
        logger.info("Fetched {} contents from repository", contentEntities.size());
        return contentEntities.stream().map(this::toContentResponse).toList();
    }

    @Override
    public ResponseEntity<String> createContent(Content content) {
        content.setId(null);
        if (content.getPlatformId() == null) {
            throw new IllegalArgumentException("Platform ID is required to create content");
        }
        Long platformId = content.getPlatformId();
        if (!validateCompany(platformId)) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Cannot create content: Platform service is unavailable.");
        }
        ContentEntity ContentEntity = ContentMapper.toEntity(content);
        ContentRepository.save(ContentEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("Content created successfully with ID: " + ContentEntity.getId());
    }

    @Override
    public ContentResponse getContentById(Long id) {
        ContentEntity entity = ContentRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Content not found with id: " + id));
        return toContentResponse(entity);
    }

    @Override
    public void deleteContentById(Long id) {
        try {
            ContentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ContentNotFoundException("Content with ID " + id + " does not exist.");
        }
    }

    @Override
    public ContentResponse updateContent(Long id, Content updatedContent) {
        ContentEntity existingContent = ContentRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Content not found with id: " + id));
        Long platformId = updatedContent.getPlatformId();
        ContentMapper.updateEntityFromBean(updatedContent, existingContent);
        if (platformId != null && validateCompany(platformId)) {
            existingContent.setPlatformId(platformId);
        } else if (platformId != null && !validateCompany(platformId)) {
            throw new IllegalArgumentException("Cannot update content: Platform service is unavailable.");
        }
        return toContentResponse(ContentRepository.save(existingContent));
    }

    @Override
    public boolean validateCompany(Long platformId) {
        return PlatformClientService.getPlatformSummary(platformId).getStatusCode().is2xxSuccessful();
    }

    @Override
    public ContentResponse toContentResponse(ContentEntity ContentEntity) {
        Content content = ContentMapper.toBean(ContentEntity);
        PlatformSummary PlatformSummary = PlatformClientService.getPlatformSummary(content.getPlatformId()).getBody();
        logger.debug("Response from Platform MS: {}", PlatformSummary.getId());
        return new ContentResponse(content, PlatformSummary);
    }

    @Override
    public List<Content> findByPlatformId(Long platformId) {
        List<ContentEntity> contentEntities = ContentRepository.findByPlatformId(platformId);
        logger.info("Fetched {} contents for platform ID {} from repository", contentEntities.size(), platformId);
        return ContentMapper.toBeanList(contentEntities);
    }

    @Override
    public void deleteByPlatformId(Long platformId) {
        List<ContentEntity> contents = ContentRepository.findByPlatformId(platformId);
        if (contents.isEmpty()) {
            return; // no contents to delete
        }
        ContentRepository.deleteAll(contents);
    }

}







