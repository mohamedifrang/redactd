package com.redactd.contentms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.redactd.contentms.bean.Content;
import com.redactd.contentms.entity.ContentEntity;
import com.redactd.contentms.response.ContentResponse;

public interface ContentService {

    List<ContentResponse> findAll();
    ResponseEntity<String> createContent(Content content);
    ContentResponse getContentById(Long id);
    void deleteContentById(Long id);
    ContentResponse updateContent(Long id, Content updatedContent);
    public ContentResponse toContentResponse(ContentEntity ContentEntity);
    boolean validateCompany(Long platformId);
    List<Content> findByPlatformId(Long platformId);
    void deleteByPlatformId(Long platformId);
}






