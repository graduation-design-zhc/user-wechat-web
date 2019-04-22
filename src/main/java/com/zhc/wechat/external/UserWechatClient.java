package com.zhc.wechat.external;

import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.dto.UserDTO;
import com.user.wechat.api.request.UserRequest;
import com.user.wechat.api.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("member/delete")
    Response<Boolean> deleteMember(@RequestParam("memberId") String memberId);

}