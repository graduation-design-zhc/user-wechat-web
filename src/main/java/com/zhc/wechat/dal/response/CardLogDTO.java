package com.zhc.wechat.dal.response;

import lombok.Data;

/**
 * @author zhanghuachang
 * @date 2019-05-01
 */
@Data
public class CardLogDTO {

    private String memberId;
    private String nickname;
    private String phone;
    private String avatar;
    private String addBalance;
    private String operator;
    private String createTime;

}
