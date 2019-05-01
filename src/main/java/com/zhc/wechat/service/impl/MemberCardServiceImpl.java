package com.zhc.wechat.service.impl;

import com.user.wechat.api.request.MemberBalanceRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.PushMessage;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.MemberCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
@Service
public class MemberCardServiceImpl implements MemberCardService {

    @Resource
    private UserWechatClient userWechatClient;

    @Resource
    private PushMessage pushMessage;

    @Override
    public Boolean addBalance(MemberBalanceRequest memberBalanceRequest) {
        Response<Boolean> response = userWechatClient.addBalance(memberBalanceRequest);
        if (response.isSuccess()) {
            pushMessage.sendAddBalanceMessage(memberBalanceRequest);
            return response.getData();
        }
        return null;
    }
}
