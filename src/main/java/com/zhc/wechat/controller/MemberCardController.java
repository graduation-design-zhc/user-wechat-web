package com.zhc.wechat.controller;

import com.user.wechat.api.request.MemberBalanceRequest;
import com.zhc.wechat.service.MemberCardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
@RestController
@RequestMapping("/wechat")
public class MemberCardController {

    @Resource
    private MemberCardService memberCardService;


    @PostMapping("member/addBalance")
    public Boolean addBalance(@RequestBody MemberBalanceRequest memberBalanceRequest) {
        return memberCardService.addBalance(memberBalanceRequest);
    }

}
