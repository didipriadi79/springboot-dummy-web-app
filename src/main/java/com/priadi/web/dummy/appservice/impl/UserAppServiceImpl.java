package com.priadi.web.dummy.appservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priadi.web.dummy.appservice.UserAppService;
import com.priadi.web.dummy.domain.BaseResponse;
import com.priadi.web.dummy.domain.dto.UserDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAppServiceImpl implements UserAppService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserAppServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<UserDto> apiGetAllUser() {
        List<UserDto> result =  new ArrayList<>();
        BaseResponse<List<Object>> response = restTemplate.getForObject("http://localhost:9091/get-all-user", BaseResponse.class);
        if (response != null)
            for(Object usr:response.getData())
            {
                UserDto userDto =  objectMapper.convertValue(usr, UserDto.class);
                result.add(userDto);
            }

        return result;
    }
}
