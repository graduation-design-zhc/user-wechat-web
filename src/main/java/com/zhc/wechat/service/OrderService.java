package com.zhc.wechat.service;

import com.user.wechat.api.dto.OrderDTO;
import com.user.wechat.api.request.OrderRequest;

/**
 * @author zhanghuachang
 * @date 2019-05-13
 */
public interface OrderService {

    Boolean memberOrder(OrderRequest orderRequest);

}
