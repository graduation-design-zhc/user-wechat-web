package com.zhc.wechat.service.impl;

import com.user.wechat.api.dto.OrderDTO;
import com.user.wechat.api.dto.OrderLogDTO;
import com.user.wechat.api.request.OrderRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.external.PushMessage;
import com.zhc.wechat.external.UserWechatClient;
import com.zhc.wechat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-13
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private UserWechatClient userWechatClient;
    @Resource
    private PushMessage pushMessage;

    @Override
    public Boolean memberOrder(OrderRequest orderRequest) {
        try {
            Response<OrderDTO> response = userWechatClient.memberOrder(orderRequest);
            if (response.isSuccess()) {
                //异步发送消息
                pushMessage.sendOrderSucessMessage(response.getData());
                return true;
            }
        } catch (Exception e) {
            log.error("购买失败！", e.getMessage());
        }
        return false;
    }

    @Override
    public List<OrderLogDTO> getOrderLogList() {
        Response<List<OrderLogDTO>> response = userWechatClient.getOrderLogList();
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }

    @Override
    public List<OrderLogDTO> getOrderLogListByPhone(String phone) {
        Response<List<OrderLogDTO>> response = userWechatClient.getOrderLogByPhone(phone);
        if (response.isSuccess()) {
            return response.getData();
        }
        return null;
    }
}
