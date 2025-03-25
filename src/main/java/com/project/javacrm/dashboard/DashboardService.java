package com.project.javacrm.dashboard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.javacrm.invoice.Invoice;
import com.project.javacrm.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DashboardService {

    ObjectMapper objectMapper = new ObjectMapper();

    public List<ChartOffer> getChartOffers() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/data-offer-chart";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        List<ChartOffer> chart = objectMapper.readValue(response.getBody(), new TypeReference<List<ChartOffer>>() {
        });
        return chart;
    }

    public List<ChartPayment> getChartPayment() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/data-payment-chart";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        List<ChartPayment> chart = objectMapper.readValue(response.getBody(), new TypeReference<List<ChartPayment>>() {
        });
        return chart;
    }

    public ChartTask getChartTask() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/data-task-chart";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        ChartTask chart = objectMapper.readValue(response.getBody(), new TypeReference<ChartTask>() {
        });
        return chart;
    }

}
