package com.zhc.wechat.service;

import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-20
 */
public interface UserService {

    List<UserDTO> getUserList();

    UserDTO getUserLogin(UserRequest userRequest);

    Boolean addUser(UserRequest userRequest);

    Boolean deleteUser(String uid);

}
