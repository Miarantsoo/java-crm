package com.project.javacrm.invoice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.javacrm.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvoiceService {

    ObjectMapper objectMapper = new ObjectMapper();

    public int totalInvoice() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/total-invoice";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        int res = Integer.parseInt(response.getBody());

        System.out.println(res);
        return res;
    }

    public double getTotalPrix() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/total-prix-invoice";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        double res = Double.parseDouble(response.getBody());

        return res;
    }

    public Pagination<InvoiceLine> getPaginateInvoiceLine(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/paginate-invoice-lines?page=" + page;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        objectMapper.findAndRegisterModules();

        Pagination<InvoiceLine> invoices = objectMapper.readValue(response.getBody(), new TypeReference<Pagination<InvoiceLine>>() {
        });

        return invoices;
    }

    public Pagination<Invoice> getPaginateInvoice(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/paginate-invoice?page=" + page;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        objectMapper.findAndRegisterModules();

        Pagination<Invoice> invoices = objectMapper.readValue(response.getBody(), new TypeReference<Pagination<Invoice>>() {
        });

        return invoices;
    }

}
