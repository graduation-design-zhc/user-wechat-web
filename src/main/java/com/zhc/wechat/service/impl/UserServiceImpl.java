package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-20
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<UserDTO> getUserList() {
        Response<List<UserDTO>> response = userWechatClient.getUserList();
        if (response.isSuccess()) {
            return response.getData();
        }
        return Collections.emptyList();
    }

    @Override
    public UserDTO getUserLogin(UserRequest userRequest) {
        return userWechatClient.getUserLogin(userRequest).getData();
    }

    @Override
    public Boolean addUser(UserRequest userRequest) {
        return userWechatClient.addUser(userRequest).getData();
    }

    @Override
    public Boolean deleteUser(String uid) {
        return userWechatClient.deleteUser(uid).getData();
    }
}
