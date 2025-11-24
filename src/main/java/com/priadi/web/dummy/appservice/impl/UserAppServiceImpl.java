package com.priadi.web.dummy.appservice.impl;

import com.priadi.web.dummy.AppConstants;
import com.priadi.web.dummy.appservice.UserAppService;
import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppServiceImpl extends BaseAppServiceImpl implements UserAppService {

    @Value("${url.user.service}")
    private String userServiceUrl;

    public UserAppServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }

    @Override
    public List<Object> apiGetAllUser() {
        String url = AppConstants.URL_PATH_GET_ALL_USER.replace("[BASE_URL]",userServiceUrl);
        BaseResDTO<List<Object>> result = new BaseResDTO<>();
        try{
            String response = restCall("",url, HttpMethod.GET);
            result = objectMapper.readValue(response, BaseResDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setCode(500);
            result.setMessage(AppConstants.MSG_INTERNAL_SERVER_ERROR);
        }

        return result.getData();
    }

    @Override
    public BaseResDTO<String> addUser(UserDTO user) {
        String url = AppConstants.URL_PATH_ADD_USER.replace("[BASE_URL]",userServiceUrl);
        BaseResDTO<String> result = new BaseResDTO<>();
        try{
            String response = restCall(objectMapper.writeValueAsString(user),url, HttpMethod.POST);
            result = objectMapper.readValue(response, BaseResDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setCode(500);
            result.setMessage(AppConstants.MSG_INTERNAL_SERVER_ERROR);
        }

        return result;
    }

    @Override
    public Object getUser(Long id) {
        String url = AppConstants.URL_PATH_GET_USER.replace("[BASE_URL]", userServiceUrl).replace("[id]", String.valueOf(id));
        BaseResDTO<Object> result = new BaseResDTO<>();
        try{
            String response = restCall("",url, HttpMethod.GET);
            result = objectMapper.readValue(response, BaseResDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setCode(500);
            result.setMessage(AppConstants.MSG_INTERNAL_SERVER_ERROR);
        }

        return result.getData();
    }

    @Override
    public BaseResDTO<String> updateUser(UserDTO user) {
        String url = AppConstants.URL_PATH_EDIT_USER.replace("[BASE_URL]", userServiceUrl);
        BaseResDTO<String> result = new BaseResDTO<>();
        try{
            String response = restCall(objectMapper.writeValueAsString(user), url, HttpMethod.PUT);
            result = objectMapper.readValue(response, BaseResDTO.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result.setCode(500);
            result.setMessage(AppConstants.MSG_INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}
