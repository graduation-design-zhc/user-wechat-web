package com.zhc.wechat.service;

import com.zhc.wechat.dal.model.WxMpMenuVO;

import java.util.List;

/**
 * @author zhanghuachang
 * @date 2019-05-19
 */
public interface WxMpMenuService {

    List<WxMpMenuVO> getMenu();

    Boolean createMenu(List<WxMpMenuVO> wxMpMenuVOS);

}
