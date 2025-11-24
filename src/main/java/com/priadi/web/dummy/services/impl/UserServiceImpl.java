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

        List<UserDTO> resultList = userAppService.apiGetAllUser();

        if(resultList != null) {
            for (UserDTO user : resultList) {
                UserModel userModel = new UserModel();
                userModel.setId(user.getId());
                userModel.setName(user.getName());
                userModel.setEmail(user.getEmail());
                userModel.setUsername(user.getUsername());

                listUser.add(userModel);
            }
        }

        return listUser;
    }

    @Override
    public BaseResDTO<String> addUser(UserModel user) {
        UserDTO userDTO = objectMapper.convertValue(user, UserDTO.class);
        BaseResDTO<String> respones = userAppService.addUser(userDTO);
        return respones;
    }
}
