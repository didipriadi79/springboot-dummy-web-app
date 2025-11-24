package com.priadi.web.dummy.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.priadi.web.dummy.appservice.UserAppService;
import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;
import com.priadi.web.dummy.model.UserModel;
import com.priadi.web.dummy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserAppService userAppService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<UserModel> getAllUser() {
        List<UserModel> listUser = new ArrayList<>();

        List<Object> resultList = userAppService.apiGetAllUser();

        if(resultList != null) {
            for (Object user : resultList) {
                UserModel userModel = objectMapper.convertValue(user, UserModel.class);

                listUser.add(userModel);
            }
        }

        return listUser;
    }

    @Override
    public BaseResDTO<String> addUser(UserModel user) {
        UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
        BaseResDTO<String> response = userAppService.addUser(userDTO);
        return response;
    }

    @Override
    public BaseResDTO<String> editUser(UserModel user) {
        UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
        BaseResDTO<String> response = userAppService.updateUser(userDTO);
        return response;
    }

    @Override
    public UserModel getUser(Long id) {
        UserModel user = objectMapper.convertValue(userAppService.getUser(id), UserModel.class);
        return user;
    }
}
