package com.project.javacrm.paiement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.javacrm.utils.Pagination;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaiementService {

    ObjectMapper objectMapper = new ObjectMapper();

    public double getTotalPaiement() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/total-paiement";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        double res = Double.parseDouble(response.getBody());

        System.out.println(res);
        return res;
    }

    public Pagination<Paiement> getPaginatePaiement(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/paginate-paiement?page=" + page;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        objectMapper.findAndRegisterModules();

        Pagination<Paiement> paiements = objectMapper.readValue(response.getBody(), new TypeReference<Pagination<Paiement>>() {
        });
        for(Paiement p : paiements.getData()) {
            p.setColorByPrice();
        }

        return paiements;
    }

    public String majMontant(String externalId, Double montant) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/update-paiement";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("external_id", externalId);
        requestBody.put("amount", montant);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, request, String.class);

        return response.getBody().replace("\"", "");
    }

    public String delPaiement(String externalId) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/delete-paiement";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("external_id", externalId);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.DELETE, request, String.class);

        return response.getBody().replace("\"", "");
    }
}
