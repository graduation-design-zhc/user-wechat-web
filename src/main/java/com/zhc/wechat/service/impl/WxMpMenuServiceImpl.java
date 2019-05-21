package com.zhc.wechat.service.impl;

import com.zhc.wechat.dal.model.WxMpMenuVO;
import com.zhc.wechat.service.WxMpMenuService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-19
 */
@Service
public class WxMpMenuServiceImpl implements WxMpMenuService {

    @Resource
    private WxMpService wxMpService;

    @Override
    public List<WxMpMenuVO> getMenu() {
        try {
            WxMpMenu wxMpMenu = wxMpService.getMenuService().menuGet();
            List<WxMpMenuVO> wxMpMenuVOS = new ArrayList<>(8);
            wxMpMenu.getMenu().getButtons().forEach(e -> {
                WxMpMenuVO wxMpMenuVO = new WxMpMenuVO();
                wxMpMenuVO.setName(e.getName());
                wxMpMenuVO.setType(e.getType());
                wxMpMenuVO.setUrl(e.getUrl());
                wxMpMenuVO.setMenuLevel("一级菜单");
                wxMpMenuVOS.add(wxMpMenuVO);
            });
            return wxMpMenuVOS;
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }
}
