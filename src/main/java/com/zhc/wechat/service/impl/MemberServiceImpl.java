package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.request.MemberRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.MemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-21
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<MemberDTO> getMemberList() {
        Response<List<MemberDTO>> response = userWechatClient.getMemberList();
        if (response.isSuccess()) {
            return response.getData();
        }
        return Collections.emptyList();
    }

    @Override
    public Boolean deleteMember(String memberId) {
        Response<Boolean> response = userWechatClient.deleteMember(memberId);
        if (response.isSuccess()) {
            return response.getData();
        }
        return false;
    }

    @Override
    public Boolean updateMMember(MemberRequest memberRequest) {
        Response<Boolean> response = userWechatClient.updateMember(memberRequest);
        if (response.isSuccess()) {
            return response.getData();
        }
        return false;
    }

    @Override
    public MemberDTO saveMember(MemberRequest memberRequest) {
        Response<MemberDTO> response = userWechatClient.addMember(memberRequest);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

}
