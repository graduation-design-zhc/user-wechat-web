package com.zhc.wechat.controller;

import com.user.wechat.api.dto.MemberDTO;
import com.zhc.wechat.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-21
 */
@RestController
@RequestMapping("/wechat")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("member/list")
    public List<MemberDTO> getMemberList() {
        return memberService.getMemberList();
    }

    @PostMapping("member/delete")
    public Boolean deleteMember(@RequestParam("memberId") String memberId) {
        return memberService.deleteMember(memberId);
    }

}