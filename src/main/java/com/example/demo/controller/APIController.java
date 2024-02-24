package com.example.demo.controller;

import com.example.demo.service.APIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class APIController {

    @Autowired
    private APIClient apiClient;

    @GetMapping("/apiResponse")
    public String getAPIResponse(Model model) {
        String apiResponse = apiClient.fetchDataFromAPI();
        model.addAttribute("apiResponse", apiResponse);
        return "apiResponse";
    }
}

