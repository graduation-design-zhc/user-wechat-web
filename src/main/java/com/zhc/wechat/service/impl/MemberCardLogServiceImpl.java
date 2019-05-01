package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.MemberCardLogDTO;
import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.convert.CardLogConvert;
import com.zhc.wechat.dal.response.CardLogDTO;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.MemberCardLogService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
@Service
public class MemberCardLogServiceImpl implements MemberCardLogService {

    @Resource
    private UserWechatClient userWechatClient;

    @Override
    public List<CardLogDTO> getAllMemberCardLog() {
        Response<List<MemberCardLogDTO>> response = userWechatClient.getAllCardLog();
        if (response.isSuccess()) {
            List<String> memberIds = response.getData()
                    .stream()
                    .map(MemberCardLogDTO::getMemberId)
                    .collect(Collectors.toList());
            List<MemberDTO> memberDTOS = userWechatClient.getMemberByMemberIds(memberIds).getData();
            Map<String, MemberDTO> map = memberDTOS
                    .stream()
                    .collect(Collectors.toMap(MemberDTO::getMemberId, Function.identity()));
            List<CardLogDTO> cardLogDTOList = response.getData()
                    .stream()
                    .map(CardLogConvert::convert)
                    .collect(Collectors.toList());
            cardLogDTOList.forEach(cardLogDTO -> {
                if (!ObjectUtils.isEmpty(map.get(cardLogDTO.getMemberId()))) {
                    cardLogDTO.setNickname(map.get(cardLogDTO.getMemberId()).getNickname());
                    cardLogDTO.setAvatar(map.get(cardLogDTO.getMemberId()).getAvatar());
                    cardLogDTO.setPhone(map.get(cardLogDTO.getMemberId()).getPhone());
                }
            });
            return cardLogDTOList;
        }
        return Collections.emptyList();
    }
}
