package com.example.demo.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIClient {

    public String fetchDataFromAPI() {
        // Make REST API call here using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://api.example.com/data";
//        return restTemplate.getForObject(apiUrl, String.class);
        return "Rest Data";
    }
}
