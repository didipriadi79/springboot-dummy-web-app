package com.priadi.web.dummy.services;

import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;
import com.priadi.web.dummy.model.UserModel;

import java.util.List;

public interface UserService {
    public List<UserModel> getAllUser();
    public BaseResDTO<String>  addUser(UserModel user);
    public BaseResDTO<String>  editUser(UserModel user);
    public UserModel getUser(Long id);
}
