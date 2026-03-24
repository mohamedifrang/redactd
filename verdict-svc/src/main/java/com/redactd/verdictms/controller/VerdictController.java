package com.redactd.verdictms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redactd.verdictms.bean.Verdict;
import com.redactd.verdictms.service.VerdictService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/verdicts")
public class VerdictController {

    private VerdictService verdictService;

    public VerdictController(VerdictService verdictService) {
        this.verdictService = verdictService;
    }

    @GetMapping
    public ResponseEntity<List<Verdict>> getAllVerdicts(@RequestParam(value = "platformId", required = true) Long platformId) {
        return new ResponseEntity<>(verdictService.findAll(platformId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addVerdict(@RequestParam(value = "platformId", required = true) Long platformId, @RequestBody Verdict verdict) {
        return verdictService.addVerdict(platformId, verdict);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVerdictsByPlatformId(@RequestParam(value = "platformId", required = true) Long platformId) {
        verdictService.deleteByPlatformId(platformId);
        return new ResponseEntity<>("Verdicts for platform with id " + platformId + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{verdictId}")
    public ResponseEntity<Verdict> getVerdictById(@PathVariable Long verdictId) {
        Verdict verdict = verdictService.findById(verdictId);
        return new ResponseEntity<>(verdict, HttpStatus.OK);
    }

    @PutMapping("/{verdictId}")
    public ResponseEntity<String> updateVerdict(@PathVariable Long verdictId, @RequestBody Verdict verdict) {
        verdictService.updateVerdict(verdictId, verdict);
        return new ResponseEntity<>("Verdict updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{verdictId}")
    public ResponseEntity<String> deleteVerdict(@PathVariable Long verdictId) {
        verdictService.deleteVerdictById(verdictId);
        return new ResponseEntity<>("Verdict deleted successfully", HttpStatus.OK);
    }
}






