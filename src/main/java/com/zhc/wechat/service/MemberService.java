package com.zhc.wechat.service;

import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.request.MemberRequest;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-04-21
 */
public interface MemberService {

    List<MemberDTO> getMemberList();

    Boolean deleteMember(String memberId);

    Boolean updateMMember(MemberRequest memberRequest);

    MemberDTO saveMember(MemberRequest memberRequest);

}
