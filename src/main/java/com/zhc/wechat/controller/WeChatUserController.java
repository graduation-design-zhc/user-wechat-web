package com.zhc.wechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghuachang
 * @date 2019-04-08
 */
@Slf4j
@RestController("weChat")
public class WeChatUserController {

    @PostMapping("/user/get/follow")
    public void getFollowUser() {

    }


}
