package com.zhc.wechat.external;

import com.user.wechat.api.dto.MemberCardDTO;
import com.user.wechat.api.dto.MemberCardLogDTO;
import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.MemberBalanceRequest;
import com.user.wechat.api.request.MemberRequest;
import com.user.wechat.api.request.UserRequest;
import com.user.wechat.api.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author zhanghuachang
 * @date 2019-04-20
 */
@FeignClient(name = "user-wechat-service")
public interface UserWechatClient {

    @PostMapping("user/list")
    Response<List<UserDTO>> getUserList();

    @PostMapping("user/login")
    Response<UserDTO> getUserLogin(@RequestBody UserRequest userRequest);

    @PostMapping("user/add")
    Response<Boolean> addUser(@RequestBody UserRequest userRequest);

    @PostMapping("user/delete")
    Response<Boolean> deleteUser(@RequestParam("uid") String uid);

    @PostMapping("member/list")
    Response<List<MemberDTO>> getMemberList();

    @RequestMapping("member/save")
    Response<MemberDTO> addMember(@RequestBody MemberRequest memberRequest);

    @PostMapping("member/delete")
    Response<Boolean> deleteMember(@RequestParam("memberId") String memberId);

    @PostMapping("member/update")
    Response<MemberDTO> updateMember(@RequestBody MemberRequest memberRequest);

    @PostMapping("member/getMemberByOpenId")
    Response<MemberDTO> getMemberByOpenId(@RequestParam("openId") String openId);

    @PostMapping("member/getMemberByMemberId")
    Response<MemberDTO> getMemberByMemberId(@RequestParam("memberId") String memberId);

    @PostMapping("member/cards")
    Response<List<MemberCardDTO>> getCardsByMemberIds(@RequestParam("memberIds") List<String> memberIds);

    @PostMapping("/member/getCardByMemberId")
    Response<MemberCardDTO> getCardByMemberId(@RequestParam("memberId") String memberId);

    @PostMapping("member/addBalance")
    Response<Boolean> addBalance(@RequestBody MemberBalanceRequest memberBalanceRequest);

    @PostMapping("member/getAllCardLog")
    Response<List<MemberCardLogDTO>> getAllCardLog();

    @PostMapping("member/getMemberByMemberIds")
    Response<List<MemberDTO>> getMemberByMemberIds(@RequestParam("memberIds") List<String> memberIds);

}
