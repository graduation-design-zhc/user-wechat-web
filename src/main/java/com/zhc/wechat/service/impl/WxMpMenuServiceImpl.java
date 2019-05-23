package com.zhc.wechat.service.impl;

import com.zhc.wechat.dal.model.WxMpMenuVO;
import com.zhc.wechat.service.WxMpMenuService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
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
@Slf4j
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

    @Override
    public Boolean createMenu(List<WxMpMenuVO> wxMpMenuVOS) {
        WxMenu wxMenu = new WxMenu();
        List<WxMenuButton> buttons = new ArrayList<>();
        wxMpMenuVOS.forEach(e -> {
            WxMenuButton button = new WxMenuButton();
            button.setUrl(e.getUrl());
            button.setName(e.getName());
            button.setType(e.getType());
            buttons.add(button);
        });
        wxMenu.setButtons(buttons);
        try {
            wxMpService.getMenuService().menuCreate(wxMenu);
            return true;
        } catch (WxErrorException e) {
            log.error("菜单创建失败!", e);
        }
        return false;
    }
}
