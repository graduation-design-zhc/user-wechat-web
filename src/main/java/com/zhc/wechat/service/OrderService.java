package com.zhc.wechat.service;

import com.user.wechat.api.dto.OrderLogDTO;
import com.user.wechat.api.request.OrderRequest;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-13
 */
public interface OrderService {

    Boolean memberOrder(OrderRequest orderRequest);

    List<OrderLogDTO> getOrderLogList();

    List<OrderLogDTO> getOrderLogListByPhone(String phone);

}
