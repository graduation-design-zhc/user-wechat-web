package com.zhc.wechat.external;

import com.user.wechat.api.dto.MemberCardDTO;
import com.user.wechat.api.dto.MemberDTO;
import com.user.wechat.api.dto.OrderDTO;
import com.user.wechat.api.dto.OrderDetailDTO;
import com.user.wechat.api.request.MemberBalanceRequest;
import com.zhc.wechat.config.WechatAccountConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
@Component
@Slf4j
public class PushMessage {

    @Resource
    private WxMpService wxMpService;

    @Resource
    private WechatAccountConfig wechatAccountConfig;

    @Resource
    private UserWechatClient userWechatClient;

    /**
     * 充值成功模板消息
     * @param memberBalanceRequest
     */
    @Async("asyncWorker")
    public void sendAddBalanceMessage(MemberBalanceRequest memberBalanceRequest) {
        MemberDTO memberDTO = userWechatClient.getMemberByMemberId(memberBalanceRequest.getMemberId()).getData();
        MemberCardDTO memberCardDTO = userWechatClient.getCardByMemberId(memberBalanceRequest.getMemberId()).getData();

        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("addBalance"));
        wxMpTemplateMessage.setToUser(memberDTO.getOpenId());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "充值成功！"),
                new WxMpTemplateData("keyword1", "¥" +memberBalanceRequest.getMemberBalance(), "#173177"),
                new WxMpTemplateData("keyword2", "¥" + memberCardDTO.getMemberBalance(), "#173177"),
                new WxMpTemplateData("keyword3", memberCardDTO.getMemberIntegral().toString(), "#173177"),
                new WxMpTemplateData("keyword4", memberCardDTO.getUpdateTime(), "#173177"),
                new WxMpTemplateData("remark", "祝您生活愉快！")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            log.info("【微信充值提醒模板消息发送成功】");
        } catch (WxErrorException e) {
            log.error("【微信充值提醒模板消息发送失败】, e={}", e);
        }
    }

    /**
     * 消费提醒模板消息
     * @param orderDTO
     */
    @Async("asyncWorker")
    public void sendOrderSucessMessage(OrderDTO orderDTO){
        MemberDTO memberDTO = userWechatClient.getMemberByMemberId(orderDTO.getMemberId()).getData();
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("consumptionDetail"));
        wxMpTemplateMessage.setToUser(memberDTO.getOpenId());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "购买成功！"),
                new WxMpTemplateData("keyword1", orderDTO.getOrderId(), "#173177"),
                new WxMpTemplateData("keyword2", orderDTO
                        .getOrderDetailDTOList()
                        .stream()
                        .map(OrderDetailDTO::getProductName)
                        .collect(Collectors.joining(", ")),
                        "#173177"),
                new WxMpTemplateData("keyword3","¥" + orderDTO.getOrderPrice(), "#173177"),
                new WxMpTemplateData("keyword4", orderDTO.getOrderIntegral().toString(), "#173177"),
                new WxMpTemplateData("keyword5", orderDTO.getCreateTime(), "#173177"),
                new WxMpTemplateData("remark", "祝您生活愉快！")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
            log.info("【微信购买成功提醒模板消息发送成功】");
        } catch (WxErrorException e) {
            log.error("【微信购买成功提醒模板消息发送失败】, e={}", e);
        }
    }

}
