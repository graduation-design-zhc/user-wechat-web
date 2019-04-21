package com.zhc.wechat.controller;

import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.zhc.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghuachang
 * @date 2019-04-09
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public UserDTO userLogin(@RequestBody UserRequest userRequest) {
        return userService.getUserLogin(userRequest);
    }


}
