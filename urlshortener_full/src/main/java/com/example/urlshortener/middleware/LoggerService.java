package com.example.urlshortener.middleware;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggerService {

    private final String LOG_API = "http://28.244.56.144/evaluation-service/logs";
    private final RestTemplate restTemplate = new RestTemplate();

    public void log(String stack, String level, String pkg, String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, Object> body = new HashMap<>();
            body.put("stack", stack);
            body.put("level", level);
            body.put("package", pkg);
            body.put("message", message);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(LOG_API, request, String.class);
        } catch (Exception e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}