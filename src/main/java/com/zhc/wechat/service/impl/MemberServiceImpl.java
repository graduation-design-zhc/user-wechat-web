package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.MemberCardDTO;
import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.request.MemberRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.convert.MemberInfoConvert;
import com.zhc.wechat.dal.response.MemberInfoDTO;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhanghuachang
 * @date 2019-04-21
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<MemberInfoDTO> getMemberList() {
        Response<List<MemberDTO>> response = userWechatClient.getMemberList();
        if (response.isSuccess() && response.getData() != null) {
            // member info
            List<MemberInfoDTO> memberInfoDTOList = response.getData().stream()
                    .map(MemberInfoConvert::convert)
                    .collect(Collectors.toList());
            //card info
            List<String> memberIs = response.getData()
                    .stream()
                    .map(MemberDTO::getMemberId)
                    .collect(Collectors.toList());
            Response<List<MemberCardDTO>> cardResponse = userWechatClient.getCardsByMemberIds(memberIs);
            if (cardResponse.isSuccess() && cardResponse.getData() != null) {
                Map<String, MemberCardDTO> cardDTOMap = cardResponse.getData()
                        .stream()
                        .collect(Collectors.toMap(MemberCardDTO::getMemberId, Function.identity()));
                memberInfoDTOList.forEach(memberInfoDTO -> {
                    if (!ObjectUtils.isEmpty(cardDTOMap.get(memberInfoDTO.getMemberId()))) {
                        memberInfoDTO.setMemberBalance(cardDTOMap.get(memberInfoDTO.getMemberId()).getMemberBalance().toString());
                        memberInfoDTO.setMemberIntegral(cardDTOMap.get(memberInfoDTO.getMemberId()).getMemberIntegral().toString());
                    }
                });
            }
            return memberInfoDTOList;
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
    public MemberDTO updateMMember(MemberRequest memberRequest) {
        Response<MemberDTO> response = userWechatClient.updateMember(memberRequest);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

    @Override
    public MemberDTO saveMember(MemberRequest memberRequest) {
        Response<MemberDTO> response = userWechatClient.addMember(memberRequest);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

    @Override
    public MemberInfoDTO getMemberByOpenId(String openId) {
        MemberDTO memberDTO = userWechatClient.getMemberByOpenId(openId).getData();
        return memberInfoConvert(memberDTO);
    }

    @Override
    public MemberDTO getMemberByMemberId(String memberId) {
        Response<MemberDTO> response = userWechatClient.getMemberByMemberId(memberId);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

    @Override
    public MemberInfoDTO getMemberByPhone(String phone) {
        MemberDTO memberDTO = userWechatClient.getMemberByPhone(phone).getData();
        return memberInfoConvert(memberDTO);
    }

    @Override
    public List<MemberInfoDTO> getMemberListByPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return Collections.emptyList();
        }
        List<MemberInfoDTO> memberInfoDTOS = new ArrayList<>(4);
        memberInfoDTOS.add(getMemberByPhone(phone));
        return memberInfoDTOS;
    }

    private MemberInfoDTO memberInfoConvert(MemberDTO memberDTO) {
        MemberInfoDTO memberInfoDTO = MemberInfoConvert.convert(memberDTO);
        MemberCardDTO memberCardDTO = userWechatClient.getCardByMemberId(memberDTO.getMemberId()).getData();
        memberInfoDTO.setMemberBalance(memberCardDTO.getMemberBalance().toString());
        memberInfoDTO.setMemberIntegral(memberCardDTO.getMemberIntegral().toString());
        return memberInfoDTO;
    }

}
