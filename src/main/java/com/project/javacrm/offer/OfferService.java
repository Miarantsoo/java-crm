package com.project.javacrm.offer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.javacrm.invoice.InvoiceLine;
import com.project.javacrm.paiement.Paiement;
import com.project.javacrm.utils.Pagination;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OfferService {

    ObjectMapper objectMapper = new ObjectMapper();

    public int totalOffers(){
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/total-offer";

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        int res = Integer.parseInt(response.getBody());

        System.out.println(res);
        return res;
    }

    public double getTotalPrix() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/total-prix-offer";
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        double res = Double.parseDouble(response.getBody());

        return res;
    }

    public Pagination<InvoiceLine> getPaginateInvoiceLine(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/paginate-offer-lines?page=" + page;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        objectMapper.findAndRegisterModules();

        Pagination<InvoiceLine> invoices = objectMapper.readValue(response.getBody(), new TypeReference<Pagination<InvoiceLine>>() {
        });

        return invoices;
    }

    public Pagination<Offer> getPaginateOffer(int page) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:80/api/paginate-offer?page=" + page;

        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        objectMapper.findAndRegisterModules();

        Pagination<Offer> offres = objectMapper.readValue(response.getBody(), new TypeReference<Pagination<Offer>>() {
        });

        return offres;
    }

}
