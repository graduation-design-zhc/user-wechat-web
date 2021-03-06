package com.zhc.wechat.controller;

import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.request.MemberRequest;
import com.zhc.wechat.dal.response.MemberInfoDTO;
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
    public List<MemberInfoDTO> getMemberList() {
        return memberService.getMemberList();
    }

    @PostMapping("member/delete")
    public Boolean deleteMember(@RequestParam("memberId") String memberId) {
        return memberService.deleteMember(memberId);
    }

    @PostMapping("member/update")
    public MemberDTO updateMember(@RequestBody MemberRequest memberRequest) {
        return memberService.updateMMember(memberRequest);
    }

    @GetMapping("member/getMember")
    public MemberInfoDTO getMemberByOpenId(@RequestParam("openId") String openId) {
        return memberService.getMemberByOpenId(openId);
    }

    @GetMapping("member/getMemberByMemberId")
    public MemberDTO getMemberByMemberId(@RequestParam("memberId") String memberId) {
        return memberService.getMemberByMemberId(memberId);
    }

    @GetMapping("member/getMemberByPhone")
    public MemberInfoDTO getMemberByPhone(@RequestParam("phone") String phone) {
        return memberService.getMemberByPhone(phone);
    }

    @GetMapping("member/getMemberListByPhone")
    public List<MemberInfoDTO> getMemberListByPhone(@RequestParam("phone") String phone) {
        return memberService.getMemberListByPhone(phone);
    }

}
