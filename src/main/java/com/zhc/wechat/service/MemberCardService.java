package com.zhc.wechat.service;

import com.user.wechat.api.request.MemberBalanceRequest;

/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
public interface MemberCardService {

    Boolean addBalance(MemberBalanceRequest memberBalanceRequest);

}
