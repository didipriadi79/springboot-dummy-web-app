package com.priadi.web.dummy.services.impl;

import com.priadi.web.dummy.appservice.UserAppService;
import com.priadi.web.dummy.domain.dto.UserDto;
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

    @Override
    public List<UserModel> getAllUser() {
        List<UserModel> listUser = new ArrayList<>();
//        UserModel user1 = new UserModel();
//        user1.setId(1L);
//        user1.setName("didi");
//        user1.setEmail("didi@gmail.com");
//        user1.setUsername("didi79");
//
//        UserModel user2 = new UserModel();
//        user2.setId(2L);
//        user2.setName("didi");
//        user2.setEmail("didi@gmail.com");
//        user2.setUsername("didi79");
//
//        listUser.add(user1);
//        listUser.add(user2);

        List<UserDto> resultList = userAppService.apiGetAllUser();

        if(resultList != null) {
            for (UserDto user : resultList) {
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
}
