package com.zhc.wechat.service;

import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.request.MemberRequest;
import com.zhc.wechat.dal.response.MemberInfoDTO;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-21
 */
public interface MemberService {

    List<MemberInfoDTO> getMemberList();

    Boolean deleteMember(String memberId);

    MemberDTO updateMMember(MemberRequest memberRequest);

    MemberDTO saveMember(MemberRequest memberRequest);

    MemberDTO getMemberByOpenId(String openId);

}
