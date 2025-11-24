package com.priadi.web.dummy.appservice;

import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;

import java.util.List;

public interface UserAppService {
    public List<Object> apiGetAllUser();
    public BaseResDTO<String> addUser(UserDTO user);

    public Object getUser(Long id);
    public BaseResDTO<String> updateUser(UserDTO user);
}
