package com.zhc.wechat.controller;

import com.zhc.wechat.dal.model.WxMpMenuVO;
import com.zhc.wechat.service.WxMpMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
@RestController
@RequestMapping("wechat")
public class WxMpMenuController {

    @Resource
    private WxMpMenuService wxMpMenuService;

    @GetMapping("/menu/getList")
    public List<WxMpMenuVO> getMenuList() {
        return wxMpMenuService.getMenu();
    }

    @PostMapping("menu/createMenu")
    public Boolean createMenu(@RequestBody List<WxMpMenuVO> menuVOS) {
        return wxMpMenuService.createMenu(menuVOS);
    }

}
