package com.zhc.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.user.wechat.api.request.MemberRequest;
import com.zhc.wechat.config.WechatAccountConfig;
import com.zhc.wechat.response.RedirectUrlDTO;
import com.zhc.wechat.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;


/**
 * @author zhanghuachang
 * @date 2019-04-25
 */
@Controller
@RequestMapping("wechat/")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Autowired
    private MemberService memberService;

    @GetMapping("/authorize")
    @ResponseBody
    public RedirectUrlDTO authorize(@RequestParam("returnUrl") String returnUrl) {
        // 1 配置 2 调用方法
        String url = wechatAccountConfig.getRedirectUrl() + "/wechat/memberInfo";
        log.info("...authorize, url={}", url);
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        RedirectUrlDTO redirectUrlDTO = new RedirectUrlDTO();
        redirectUrlDTO.setUrl(redirectUrl);
        return redirectUrlDTO;
    }

    @GetMapping("/memberInfo")
    public String memberInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl) {
        log.info("memberInfo, code={}", code);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        WxMpUser wxMpUser = new WxMpUser();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
        }
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setOpenId(wxMpUser.getOpenId());
        memberRequest.setGender(wxMpUser.getSex());
        memberRequest.setAvatar(wxMpUser.getHeadImgUrl());
        memberRequest.setNickname(wxMpUser.getNickname());
        memberService.saveMember(memberRequest);
        return "redirect:" + returnUrl + "?openid=" + wxMpUser.getOpenId();
    }

}
