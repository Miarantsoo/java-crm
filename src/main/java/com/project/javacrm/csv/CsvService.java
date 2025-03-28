package com.project.javacrm.csv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {

    private final ObjectMapper objectMapper;

    public CsvService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<String> importDataFromCSV(MultipartFile file) {
        BufferedReader br;
        List<String> result = new ArrayList<>();
        try {

            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String importDataToLaravel(MultipartFile file) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/import-client-duplication";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<String> data = importDataFromCSV(file);

        String client = "";
        List<String> projects = new ArrayList<>();
        List<String> invoices = new ArrayList<>();
        List<String> invoiceLines = new ArrayList<>();
        boolean enteringProject = false;
        boolean enteringInvoices = false;
        boolean enteringInvoiceLines = false;

        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).equals("client")) {
                client += data.get(i+1);
            } else if (data.get(i).equals("projet")) {
                enteringProject = true;
                continue;
            } else if(data.get(i).equals("invoice")) {
                enteringProject = false;
                enteringInvoices = true;
                continue;
            }

            if(enteringProject) {
                projects.add(data.get(i));
            } else if(enteringInvoices) {
                if(!enteringInvoiceLines) {
                    invoices.add(data.get(i));
                    enteringInvoiceLines = true;
                } else {
                    invoiceLines.add(data.get(i));
                    enteringInvoiceLines = false;
                }
            }
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("client", client);
        requestBody.put("project", projects);
        requestBody.put("invoice",  invoices);
        requestBody.put("invoiceLine",  invoiceLines);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        System.out.println(response.getBody());
        return response.getBody();
    }

}
