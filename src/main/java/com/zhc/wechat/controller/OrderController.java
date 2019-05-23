package com.zhc.wechat.controller;

import com.user.wechat.api.dto.OrderLogDTO;
import com.user.wechat.api.request.OrderRequest;
import com.user.wechat.api.response.Response;
import com.zhc.wechat.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Boolean memberOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.memberOrder(orderRequest);
    }

    @GetMapping("member/getOrderLog")
    public List<OrderLogDTO> getOrderLog() {
        return orderService.getOrderLogList();
    }

    @GetMapping("member/getOrderLogByPhone")
    public List<OrderLogDTO> getOrderLogByPhone(@RequestParam("phone") String phone) {
        return orderService.getOrderLogListByPhone(phone);
    }

}
