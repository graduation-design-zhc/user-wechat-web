package com.zhc.wechat.controller;

import com.zhc.wechat.dal.response.CardLogDTO;
import com.zhc.wechat.service.MemberCardLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
@RestController
@RequestMapping("wechat/")
public class MemberCardLogController {

    @Resource
    private MemberCardLogService memberCardLogService;

    @GetMapping("member/getAllCardLog")
    public List<CardLogDTO> getAllCardLog() {
        return memberCardLogService.getAllMemberCardLog();
    }

}
