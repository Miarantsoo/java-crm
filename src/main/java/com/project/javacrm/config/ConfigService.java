package com.project.javacrm.config;


import com.project.javacrm.user.LaravelError;
import com.project.javacrm.user.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigService {

    public String sendRemiseToLaravel(Double remise){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/configuration";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("val", remise);

        HttpEntity<Map<String, Double>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        String res = response.getBody();

        return res;
    }

}
