package com.priadi.web.dummy.appservice;

import com.priadi.web.dummy.domain.BaseResponse;
import com.priadi.web.dummy.domain.dto.UserDto;

import java.util.List;

public interface UserAppService {
    public List<UserDto> apiGetAllUser();
}
