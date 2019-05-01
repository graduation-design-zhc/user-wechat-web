package com.zhc.wechat.dal.response;

import lombok.Data;

/**
 * @author zhanghuachang
 * @date 2019-04-29
 */
@Data
public class MemberInfoDTO {

    //member

    private String memberId;
    private String openId;
    private String avatar;
    private Integer gender;
    private String phone;
    private String nickname;
    private String birthday;
    private String createTime;
    private String updateTime;

    //card

    private String memberBalance;
    private String memberIntegral;

}
