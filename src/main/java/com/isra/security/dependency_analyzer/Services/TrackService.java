package com.isra.security.dependency_analyzer.Services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class TrackService {
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> uploadSbomFromFile(String projectUuid, String filePath){
        String apiUrl = System.getenv("DEPENDENCY_TRACK_API_URL") + "/sendSbom";
        String apiKey = System.getenv("DEPENDENCY_TRACK_API_KEY");

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Api-Key", apiKey);

        // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("project", projectUuid);
        requestBody.put("bom", bomJson);
        requestBody.put("autoCreate", true);

        // Create HTTP request entity
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send PUT request to Dependency-Track API
        return restTemplate.exchange(apiUrl, HttpMethod.PUT, requestEntity, String.class);
    }
}
