package com.redactd.platformms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redactd.platformms.bean.Platform;
import com.redactd.platformms.response.PlatformResponse;
import com.redactd.platformms.service.PlatformService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/platforms")
public class PlatformController {

    private PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping
    public ResponseEntity<List<Platform>> getAllPlatforms() {
        return new ResponseEntity<>(platformService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Platform> createPlatform(@RequestBody Platform platform) {
        return ResponseEntity.ok(platformService.createPlatform(platform));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PlatformResponse> getPlatformById(@PathVariable Long id) {
        return ResponseEntity.ok(platformService.getPlatformById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlatformById(@PathVariable Long id, @RequestBody Platform updatedPlatform) {
        platformService.updatePlatform(id, updatedPlatform);
        return ResponseEntity.ok("Platform updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlatformById(@PathVariable Long id) {
        platformService.deletePlatformById(id);
        return ResponseEntity.ok("Platform deleted successfully");
    }
}






