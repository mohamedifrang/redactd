package com.redactd.contentms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redactd.contentms.bean.Content;
import com.redactd.contentms.response.ContentResponse;
import com.redactd.contentms.service.ContentService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/contents")
public class ContentController {

    private ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping
    public ResponseEntity<List<ContentResponse>> getAllContents() {
        return ResponseEntity.ok(contentService.findAll());
    }

    @GetMapping(params = "platformId")
    public ResponseEntity<List<Content>> getContentByPlatform(@RequestParam(value = "platformId", required = true) Long platformId) {
        return ResponseEntity.ok(contentService.findByPlatformId(platformId));
    }
    
    @PostMapping
    public ResponseEntity<String> createContent(@RequestBody Content content) {
        return contentService.createContent(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getContentById(@PathVariable Long id) {
        ContentResponse contentResponse = contentService.getContentById(id);
        return new ResponseEntity<>(contentResponse, (contentResponse == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable Long id) {
        contentService.deleteContentById(id);
        return new ResponseEntity<>("Content deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteContentByPlatform(@RequestParam(value = "platformId", required = true) Long platformId) {
        contentService.deleteByPlatformId(platformId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponse> updateContent(@PathVariable Long id, @RequestBody Content updatedContent) {
        return ResponseEntity.ok(contentService.updateContent(id, updatedContent));
    }
}






