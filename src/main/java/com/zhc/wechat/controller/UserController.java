package com.zhc.wechat.controller;

import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.zhc.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-20
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/list")
    public List<UserDTO> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("/user/add")
    public Boolean addUser(@RequestBody UserRequest userRequest) {
        //TODO
        return true;
    }

    @PostMapping("/user/delete")
    public Boolean deleteUser(@RequestParam("id") String uid) {
        return true;
    }

}
