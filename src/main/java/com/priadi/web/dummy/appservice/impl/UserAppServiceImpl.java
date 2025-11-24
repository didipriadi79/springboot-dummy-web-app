package com.priadi.web.dummy.appservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priadi.web.dummy.AppConstants;
import com.priadi.web.dummy.appservice.UserAppService;
import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${url.user.service}")
    private String userServiceUrl;

    @Override
    public List<UserDTO> apiGetAllUser() {
        List<UserDTO> result =  new ArrayList<>();
        BaseResDTO<List<Object>> response = restTemplate.getForObject(AppConstants.URL_PATH_GET_ALL_USER.replace("[BASE_URL]",userServiceUrl), BaseResDTO.class);
        if (response != null)
            for(Object usr:response.getData())
            {
                UserDTO userResDTO =  objectMapper.convertValue(usr, UserDTO.class);
                result.add(userResDTO);
            }

        return result;
    }

    @Override
    public BaseResDTO<String> addUser(UserDTO user) {
        BaseResDTO<String> response = restTemplate.postForObject(AppConstants.URL_PATH_ADD_USER.replace("[BASE_URL]",userServiceUrl), user, BaseResDTO.class);
        return response;
    }
}
