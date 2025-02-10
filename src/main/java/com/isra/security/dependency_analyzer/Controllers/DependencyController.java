package com.isra.security.dependency_analyzer.Controllers;

import com.isra.security.dependency_analyzer.DTOs.SBOMRequest;
import com.isra.security.dependency_analyzer.DTOs.VulnerabilityResponse;
import com.isra.security.dependency_analyzer.Services.DependencyService;
import com.isra.security.dependency_analyzer.Services.SBOMGeneratorService;
import com.isra.security.dependency_analyzer.Services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "dependency-analyzer")
public class DependencyController {
   /* @Autowired
    private DependencyService dependencyService;

    @Autowired
    private TrackService trackService;*/

    @Autowired
    private SBOMGeneratorService sbomGeneratorService;

    /*
    @PostMapping("generateBom")
    public ResponseEntity<String> generateBom(@RequestBody SBOMRequest sbomRequest){
        try {
            File bomFile = dependencyService.generateBomJson(sbomRequest);
            return ResponseEntity.ok("BOM file generated successfully: " + bomFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating BOM file: " + e.getMessage());
        }
    }*/

    @GetMapping("/generateSbom")
    public ResponseEntity<String> generateSBOM(@RequestParam("path") String pomFilePath) {
        try {
            // Generate SBOM from the provided pom.xml path
            String sbom = sbomGeneratorService.generateSBOM(pomFilePath);
            return ResponseEntity.ok(sbom);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing pom.xml at path: " + pomFilePath);
        }
    }


}
