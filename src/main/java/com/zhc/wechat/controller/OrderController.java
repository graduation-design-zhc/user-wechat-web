package com.zhc.wechat.controller;

import com.user.wechat.api.request.OrderRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhanghuachang
 * @date 2019-05-13
 */
@RestController
@RequestMapping("wechat")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("member/order")
    public Response memberOrder(@RequestBody OrderRequest orderRequest) {
        return Response.success(orderService.memberOrder(orderRequest));
    }

}
