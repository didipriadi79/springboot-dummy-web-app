package com.priadi.web.dummy.appservice;

import com.priadi.web.dummy.dto.BaseResDTO;
import com.priadi.web.dummy.dto.UserDTO;

import java.util.List;

public interface UserAppService {
    public List<UserDTO> apiGetAllUser();
    public BaseResDTO<String> addUser(UserDTO user);
}
