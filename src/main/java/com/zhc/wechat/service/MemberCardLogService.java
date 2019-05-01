package com.zhc.wechat.service;

import com.zhc.wechat.dal.response.CardLogDTO;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
public interface MemberCardLogService {

    List<CardLogDTO> getAllMemberCardLog();

}
