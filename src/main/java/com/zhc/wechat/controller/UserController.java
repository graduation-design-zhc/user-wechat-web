package com.zhc.wechat.controller;

import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.zhc.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-20
 */
@RestController
@RequestMapping("wechat")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public UserDTO userLogin(@RequestBody UserRequest userRequest) {
        return userService.getUserLogin(userRequest);
    }

    @PostMapping("/user/list")
    public List<UserDTO> getUserList() {
        return userService.getUserList();
    }

    @PostMapping("/user/add")
    public Boolean addUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @PostMapping("/user/delete")
    public Boolean deleteUser(@RequestParam("id") String uid) {
        return userService.deleteUser(uid);
    }

}
