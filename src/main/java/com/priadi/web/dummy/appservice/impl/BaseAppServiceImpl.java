package com.priadi.web.dummy.appservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priadi.web.dummy.AppConstants;
import com.priadi.web.dummy.dto.BaseResDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseAppServiceImpl {
    private final RestTemplate restTemplate;
    public final ObjectMapper objectMapper = new ObjectMapper();

    public BaseAppServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String restCall(String payload, String url, HttpMethod method) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                method,
                requestEntity,
                String.class
        );

        return response.getBody();
    }
}
